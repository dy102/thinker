export interface ThinkingDtos {
  thinkingId?: number;
  thinkingThumbnail?: string | null;
  thinkingWriter?: string;
  thinkingTitle?: string;
  isPremium?: boolean;
  likeCount?: number;
  repliesCount?: number;
  viewCount?: number;
}

export interface IThinkingPremium {
  premiumThinkingCount?: number;
  premiumThinkingDtos?: ThinkingDtos[];
}
