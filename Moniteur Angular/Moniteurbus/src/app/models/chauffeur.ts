import { Role } from "../enum/role";

export class Chauffeur {
    idUtilisateur?:number;
    nom?: string;
    prenom?: string;
    adresse?:string;
    dateNaissance?: Date;
    tel?: string;
    email?: string;
    password?: string;
    roleEnum?: Role;
}
