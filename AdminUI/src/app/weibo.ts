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

export class WeiboStatic {
    count: Number;
    time: Date;
}

export class WeiboStaticDTO {
    msg: string;
    state: string;
    weiboTimeCount: WeiboStatic[];
}

export class CommentStatic {
    count: Number;
    time: Date;
}

export class CommentStaticDTO {
    msg: string;
    state: string;
    commentTimeCount: CommentStatic[];
}
