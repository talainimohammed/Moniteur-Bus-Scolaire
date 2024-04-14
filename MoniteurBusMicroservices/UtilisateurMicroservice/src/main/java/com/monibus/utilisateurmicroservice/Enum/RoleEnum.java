package com.monibus.utilisateurmicroservice.Enum;

public enum RoleEnum {
    ADMINISTRATEUR(0),
    PARENT(1),
    CHAUFFEUR(2),
    Ecole(3);

    private int role;
    RoleEnum(int i) {
        this.role=i;
    }
    public static RoleEnum getRoleEnum(int role){
        for (RoleEnum roleEnum:RoleEnum.values()){
            if (roleEnum.role==role){
                return roleEnum;
            }
        }
        throw new IllegalArgumentException("Role non trouvé");
    }
}
