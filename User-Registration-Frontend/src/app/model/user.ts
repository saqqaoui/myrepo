import { Gender } from './gender';

export class User {
  id: number;
  name: string;
  email: string;
  gender: Gender
  inscriptionDate: Date;
  updateDate: Date;

  constructor()  {
  }

}
