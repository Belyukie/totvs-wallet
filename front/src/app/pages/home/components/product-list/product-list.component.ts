import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Product } from "src/app/interfaces/product.interface";
import { User } from "src/app/interfaces/user.interface";

@Component({
  selector: "product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
})
export class ProductListComponent {
  @Input() user: User;
  @Input() products: Array<Product>;
  @Output() onRescue = new EventEmitter<Product>();

  /**
   * Emite o evento de resgate de um produto
   * @param {Product} product produto que será resgatado
   */
  public rescueProduct = (product: Product) => this.onRescue.emit(product);
}
