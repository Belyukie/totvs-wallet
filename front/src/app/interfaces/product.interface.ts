export interface Product {
  id: number;
  title: string;
  image: string;
  price: number;
}

export class ProductConvert {
  public static toProduct(json: string): Product {
    return JSON.parse(json);
  }

  public static productToJson(value: Product): string {
    return JSON.stringify(value);
  }
}
