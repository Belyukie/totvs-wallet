import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from "axios";
import { RestAPIConstants } from "../constants/rest-api.constants";
import { StorageConstants } from "../constants/storage.constants";

export class RestAPIProvider {
  private axiosInstance: AxiosInstance = axios.create({
    baseURL: RestAPIConstants.BASE_URL,
    headers: {
      [RestAPIConstants.CONTENT_TYPE_KEY]:
        RestAPIConstants.CONTENT_TYPE_DEFAULT_VALUE,
    },
  });

  constructor() {
    this.axiosInstance.interceptors.request.use(
      this._requestsInterceptor,
      (error: any) => Promise.reject(error)
    );
  }

  /**
   * @private
   * Intercepta todas as requisições
   * @param {AxiosRequestConfig} requestConfig Configurações da requisição
   * @returns {AxiosRequestConfig | Promise<AxiosRequestConfig>}  Configurações após interceptação
   */
  private async _requestsInterceptor(
    requestConfig: AxiosRequestConfig
  ): Promise<AxiosRequestConfig> {
    const bearerToken: string | null = localStorage.getItem(
      StorageConstants.BEARER_TOKEN
    );

    if (bearerToken)
      requestConfig.headers.Authorization = `${RestAPIConstants.BEARER_TOKEN_PREFIX}${bearerToken}`;

    return requestConfig;
  }

  /**
   * @public
   * Retorna os produtos
   * @returns {Promise<AxiosResponse<any>>} Resultado da requisição
   */
  public getProducts = (): Promise<AxiosResponse<any>> =>
    this.axiosInstance.request({
      method: RestAPIConstants.GET_METHOD,
      url: RestAPIConstants.PRODUCTS,
    });
}
