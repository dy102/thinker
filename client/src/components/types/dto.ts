// thinking premiumn 요청값
export interface IThinkingPremiumParams {
  page: number;
}

// thinking premium 반환값
export interface ThinkingDtos {
  thinkingId?: number;
  thinkingThumbnail: string | null;
  thinkingWriter?: string;
  thinkingTitle?: string;
  isPremium?: boolean;
  likeCount?: number;
  repliesCount?: number;
  viewCount?: number;
}

export interface IThinkingPremium {
  premiumThinkingCount: number;
  dtos?: ThinkingDtos[];
}

// normal thinking 요청값
export interface IThinkingNormalParams {
  kind: string;
  size: number;
  lastId: number | null;
}

// normal thinking 반환값
export interface IThinkingNormalDtos {
  thinkingId: 0;
  thinkingThumbnail: [string];
  thinkingWriter: string;
  thinkingTitle: string;
  isPremium: boolean;
  likeCount: number;
  repliesCount: number;
  viewCount: number;
}
export interface IThinkingNormal {
  contents: {
    dtos: IThinkingNormalDtos[];
  };
  totalElements: number;
  nextCursor: number;
}

// thinking 보기 요청값
export interface IThinkingParams {
  thinkingId: number;
}
