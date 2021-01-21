import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { AppBarComponent } from "./app-bar.component";

@NgModule({
  imports: [CommonModule],
  declarations: [AppBarComponent],
  exports: [AppBarComponent],
})
export class AppBarModule {}
