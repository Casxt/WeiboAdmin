export class Administrator {
    nickname: string;
    hashPass: string;
}

export class AdministratorDTO {
    state: string;
    msg: string;
    admin: Administrator;
}
