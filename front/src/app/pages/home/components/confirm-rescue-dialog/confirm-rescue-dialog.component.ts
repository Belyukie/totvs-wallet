import { Component, Inject } from "@angular/core";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Product } from "../../../../interfaces/product.interface";
import { User } from "../../../../interfaces/user.interface";

@Component({
  selector: "confirm-rescue-dialog",
  templateUrl: "./confirm-rescue-dialog.component.html",
  styleUrls: ["./confirm-rescue-dialog.component.scss"],
})
export class ConfirmRescueDialogComponent {
  public user: User;
  public product: Product;
  public quantityForm: FormGroup;
  constructor(
    public dialogRef: MatDialogRef<ConfirmRescueDialogComponent>,
    @Inject(MAT_DIALOG_DATA)
    public data: {
      user: User;
      product: Product;
    },
    private formBuilder: FormBuilder
  ) {
    this.user = data.user;
    this.product = data.product;

    this.quantityForm = this.formBuilder.group({
      quantity: new FormControl(1, [Validators.required]),
    });
  }

  /**
   * Informa se o usuário pode ou efetuar o resgate
   */
  public canRescue = (): boolean =>
    this.user.balance >= this.product.price * this.quantityForm.value.quantity;

  /**
   * Fecha o modal
   */
  public close = (): void =>
    this.dialogRef.close({
      accepted: false,
      quantity: 0,
    });

  /**
   * Confirma o resgate
   */
  public confirm = (): void =>
    this.dialogRef.close({
      accepted: true,
      quantity: this.quantityForm.value.quantity,
    });
}
