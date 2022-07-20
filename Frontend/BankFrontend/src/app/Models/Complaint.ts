import {User} from "../core/models/user";

export class Complaint {
  idComplaint:number
  message: string
  subject:string
  status:string
  user : User

}
