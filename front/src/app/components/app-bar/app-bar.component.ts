import { Component, enableProdMode } from "@angular/core";
import { AngularFirestore } from "@angular/fire/firestore";
import { Observable } from "rxjs";
import { FirestoreConstants } from "src/app/constants/firestore.constant";
import { TestConstants } from 'src/app/constants/test.constant';

enableProdMode();
@Component({
  selector: "app-bar",
  templateUrl: "./app-bar.component.html",
  styleUrls: ["./app-bar.component.scss"],
})
export class AppBarComponent {
  public user: Observable<any>;

  constructor(private firestore: AngularFirestore) {
    this._loadUser();
  }

  /**
   * Carrega o usu�rio
   * @returns {Observable<any>} Observ�vel com o usu�rio
   */
  private _loadUser = (): Observable<any> =>
    (this.user = this.firestore
      .doc(`${FirestoreConstants.USERS}/${TestConstants.USER_ID}`)
      .valueChanges());
}
