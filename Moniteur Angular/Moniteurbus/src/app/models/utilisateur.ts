import { Role } from "../enum/role";

export class Utilisateur {
    idUtilisateur?:number;
    nom?: string;
    prenom?: string;
    adresse?:string;
    dateNaissance?: Date;
    tel?: string;
    email?: string;
    password?: string;
    idEcole?: number;
    roleEnum?: Role;
}
