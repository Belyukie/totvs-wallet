import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { ProductListComponent } from "./product-list.component";

@NgModule({
  imports: [CommonModule, MatCardModule, MatButtonModule],
  declarations: [ProductListComponent],
  exports: [ProductListComponent],
})
export class ProductListModule {}
