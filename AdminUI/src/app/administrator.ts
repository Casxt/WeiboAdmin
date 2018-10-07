export class Administrator {
    nickname: string;
    hashPass: string;
    administratorDate: string;
}

export class AdministratorDTO {
    state: string;
    msg: string;
    admin: Administrator;
}
