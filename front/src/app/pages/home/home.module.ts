import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MatDialogModule } from "@angular/material/dialog";
import { MatProgressBarModule } from "@angular/material/progress-bar";
import { NgParticlesModule } from "ng-particles";
import { ProductListModule } from "src/app/pages/home/components/product-list/product-list.module";
import { RestAPIProvider } from "src/app/providers/rest-api.provider";
import { ConfirmRescueDialogComponent } from "./components/confirm-rescue-dialog/confirm-rescue-dialog.component";
import { ConfirmRescueDialogModule } from "./components/confirm-rescue-dialog/confirm-rescue-dialog.module";
import { HomeComponent } from "./home.component";

@NgModule({
  entryComponents: [ConfirmRescueDialogComponent],
  imports: [
    CommonModule,
    ProductListModule,
    MatProgressBarModule,
    NgParticlesModule,
    MatDialogModule,
    ConfirmRescueDialogModule,
  ],
  declarations: [HomeComponent, ConfirmRescueDialogComponent],
  providers: [RestAPIProvider],
  exports: [HomeComponent],
})
export class HomeModule {}
