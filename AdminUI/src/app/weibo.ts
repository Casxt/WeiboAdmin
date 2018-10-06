import { User } from './user';

export class AuditWeibo {
    isAudited: boolean;
    weiboId: Number;
    weibo: Weibo;
}

export class Weibo {
    commentCount: Number;
    content: string;
    id: Number;
    isBaned: boolean;
    isDeleted: boolean;
    userId: User;
}

export class WeiboDTO {
    state: string;
    msg: string;
    auditWeibo: AuditWeibo[];
}
