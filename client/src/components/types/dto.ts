export interface IThinkingPremium {
  premiumThinkingCount?: number;
  premiumThinkingDtos?: {
    thinkingId?: number;
    thinkingThumbnail?: string | null;
    thinkingWriter?: string;
    thinkingTitle?: string;
    isPremium?: boolean;
    likeCount?: number;
    repliesCount?: number;
    viewCount?: number;
  }[];
}
