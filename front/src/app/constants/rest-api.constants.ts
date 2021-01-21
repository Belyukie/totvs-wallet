type HTTP_METHOD = "GET" | "POST" | "PATCH" | "DELETE" | "PUT";

export class RestAPIConstants {
  /*
   * HTTP Methods
   */
  static readonly GET_METHOD: HTTP_METHOD = "GET";
  static readonly POST_METHOD: HTTP_METHOD = "POST";
  static readonly PATCH_METHOD: HTTP_METHOD = "PATCH";
  static readonly DELETE_METHOD: HTTP_METHOD = "DELETE";
  static readonly PUT_METHOD: HTTP_METHOD = "PUT";

  /*
   * Base codes
   */
  static readonly CONTENT_TYPE_KEY: string = "Content-Type";
  static readonly CONTENT_TYPE_DEFAULT_VALUE: string = "application/json";
  static readonly CONTENT_TYPE_MULTIPART: string = "multipart/form-data";
  static readonly BEARER_TOKEN_PREFIX: string = "Bearer ";

  /*
   * Base settings
   */
  static readonly SCHEME: string = "http";
  static readonly HOST: string = "localhost";
  static readonly PORT: string = "3000";
  static readonly BASE_PATH: string = "";
  static readonly BASE_URL: string = `${RestAPIConstants.SCHEME}://${RestAPIConstants.HOST}:${RestAPIConstants.PORT}${RestAPIConstants.BASE_PATH}`;

  /*
   * Products
   */
  static readonly PRODUCTS: string = `/products`;
  /*             */
}
