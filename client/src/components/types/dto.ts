
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

// thinking 요청값
export interface IThinkingParams {
  kind: string;
  size: number;
  lastId: number | null;
}

// thinking 반환값
export interface IThinkingDtos {
  thinkingId: 0,
  thinkingThumbnail: [
    string
  ];
  thinkingWriter: string;
  thinkingTitle: string;
  isPremium: boolean;
  likeCount: number;
  repliesCount: number;
  viewCount: number;
}
export interface IThinking {
  contents: {
    dtos: IThinkingDtos[];
  };
  totalElements: number;
  nextCursor: number;
}