import { browser, by, element } from "protractor";

export class AppPage {
  async navigateTo() {
    return await browser.get("/");
  }

  getParagraphText() {
    return element(by.css("app-root h1")).getText();
  }

  getResgate() {
    return element.all(by.css(".mat-button-wrapper:nth-child(1)")).first();
  }

  getBalance() {
    return element.all(by.css("#protractor-marcio"));
  }

  getAppName() {
    return element.all(by.css(".app-name"));
  }

  getConfirmacao() {
    return element.all(by.css(".protractor-confirma")).first();
  }
  getCancelar() {
    return element.all(by.css("mat-warn"));
  }
}
