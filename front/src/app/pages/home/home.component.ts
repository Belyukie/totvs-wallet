import { Component } from "@angular/core";
import { AngularFirestore } from "@angular/fire/firestore";
import {
  MatDialog,
  MAT_DIALOG_DEFAULT_OPTIONS,
} from "@angular/material/dialog";
import { AxiosResponse } from "axios";
import { Subscription } from "rxjs";
import { FirestoreConstants } from "src/app/constants/firestore.constant";
import { HTTPConstants } from "src/app/constants/HTTP.constants";
import { TestConstants } from "src/app/constants/test.constant";
import { Product } from "src/app/interfaces/product.interface";
import { User } from "src/app/interfaces/user.interface";
import { RestAPIProvider } from "src/app/providers/rest-api.provider";
import { ConfirmRescueDialogComponent } from "./components/confirm-rescue-dialog/confirm-rescue-dialog.component";

@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
  providers: [
    { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: false } },
  ],
})
export class HomeComponent {
  public isBusy: boolean;
  public userSubscription: Subscription;
  public user: User;
  public products: Array<Product> = [];
  public particlesOptions = {
    background: {
      color: {
        value: "#ffffff",
      },
    },
    fpsLimit: 60,
    interactivity: {
      detectsOn: "window",
      events: {
        onHover: {
          enable: true,
          mode: "repulse",
        },
        resize: true,
      },
      modes: {
        bubble: {
          distance: 400,
          duration: 2,
          opacity: 0.8,
          size: 40,
          speed: 3,
        },
        push: {
          quantity: 4,
        },
        repulse: {
          distance: 200,
          duration: 0.4,
        },
      },
    },
    particles: {
      color: {
        value: "#009ec0",
      },
      links: {
        color: "#009ec0",
        distance: 150,
        enable: true,
        opacity: 0.5,
        width: 1,
      },
      collisions: {
        enable: true,
      },
      move: {
        direction: "none",
        enable: true,
        outMode: "bounce",
        random: false,
        speed: 6,
        straight: false,
      },
      number: {
        density: {
          enable: true,
          value_area: 800,
        },
        value: 80,
      },
      opacity: {
        value: 0.5,
      },
      shape: {
        type: "circle",
      },
      size: {
        random: true,
        value: 5,
      },
    },
    detectRetina: true,
  };

  constructor(
    private firestore: AngularFirestore,
    private restAPIProvider: RestAPIProvider,
    public dialog: MatDialog
  ) {}

  /**
   * Carrega a tela
   * @returns {Promise<void>} Resultado do carregamento da tela
   */
  public async ngOnInit(): Promise<void> {
    this.isBusy = true;

    this._loadUser();
    await this._loadProducts();

    this.iniciaSaldo();

    this.isBusy = false;
  }

  public ngOnDestroy(): void {
    this.userSubscription.unsubscribe();
  }

  /**
   * Carrega o usuário
   * @returns {Subscription} Observável com o usuário
   */
  private _loadUser = (): Subscription =>
    (this.userSubscription = this.firestore
      .doc(`${FirestoreConstants.USERS}/${TestConstants.USER_ID}`)
      .valueChanges()
      .subscribe((user: User) => (this.user = user)));

  /**
   * Carrega os produtos
   * @returns {Promise<void>} Resultado do carregamento dos produtos
   */
  private _loadProducts = async (): Promise<void> => {
    const productsResponse: AxiosResponse<any> = await this.restAPIProvider.getProducts();

    if (productsResponse.status === HTTPConstants.OK)
      this.products = productsResponse.data;
  };

  /**
   * Resgata um produto
   * @param {Product} product Produto que será resgatado
   * @param {number} quantity Quantidade de produtos
   * @returns {Promise<void>} Resultado do resgate
   */
  public _rescueProduct = (
    product: Product,
    quantity: number
  ): Promise<void> =>
    this.firestore
      .doc(`${FirestoreConstants.USERS}/${TestConstants.USER_ID}`)
      .update({
        balance: this.user.balance - product.price * quantity,
      });

  /**
   * Mostra o modal de confirmação de resgate
   * @param {Product} product Produto que será resgatado
   */
  public showConfirmRescueModal(product: Product): void {
    const dialogRef = this.dialog.open(ConfirmRescueDialogComponent, {
      width: "400px",
      data: {
        user: this.user,
        product,
      },
    });

    dialogRef.afterClosed().subscribe(({ accepted, quantity }) => {
      if (accepted) this._rescueProduct(product, quantity);
    });
  }

  public iniciaSaldo() {
    this.firestore
      .doc(`${FirestoreConstants.USERS}/${TestConstants.USER_ID}`)
      .update({
        balance: 300,
      });
  }
}
