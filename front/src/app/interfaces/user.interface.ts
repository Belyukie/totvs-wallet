export interface User {
  name: string;
  email: string;
  balance: number;
}

// Converts JSON strings to/from your types
export class UserConvert {
  public static toUser(json: string): User {
    return JSON.parse(json);
  }

  public static userToJson(value: User): string {
    return JSON.stringify(value);
  }
}
