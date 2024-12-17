namespace DataTransfer {
  export interface LoginDetailsDTO {
    username: string;
    email: string;
    password: string;
  }
  export interface RegisterDetailsDTO {
    fullName: string;
    username: string;
    email: string;
    password: string;
    passwordAgain: string;
  }
}
export default DataTransfer;
