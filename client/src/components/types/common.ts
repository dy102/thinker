// Modal Type
export type ModalType = {
  open: boolean;
  onClose: () => void;
};

// PremiumSurvey Type
export type SurveyType = {
  surveyId: number;
  surveyImg: string | null;
  surveyWriter: string;
  surveyTitle: string;
  surveyItemCount: number;
  isDone: boolean;
  isPremium: boolean;
};

// PremiumThinking Type
export type ThinkingType = {
  thinkingThumbnail: string | null;
  thinkingId: number;
  thinkingWriter?: string;
  thinkingTitle?: string;
  likecount?: number;
  repliesCount?: number;
  viewCount?: number;
};

// Reply Type
export type ReplyType = {
  userId: number;
  replyContent: string;
  likeCount: number;
  createdAt: string;
  isLiked: boolean;
};
