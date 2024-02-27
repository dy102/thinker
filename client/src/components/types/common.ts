// Modal Type
export type ModalType = {
  open: boolean;
  onClose: () => void;
};

// Survey Types

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

// MultipleChoice Survey Item Type
export type MultipleSurveyItemType = {
  itemId: number;
  item: string;
  isCheck: boolean;
};

export type MultipleSurveyItemComponentType = {
  multipleChoiceId: number;
  itemId: number;
  item: string;
  isCheck: boolean;
};

// MultipleChoice Suevey Type
export type MultipleSurveyType = {
  multipleChoiceId: number;
  question: string;
  items: MultipleSurveyItemType[];
  isDone: boolean;
};

// Subjective Survey Type
export type SubjectiveContentDoneType = {
  subjectiveFormId: number;
  question: string;
  answer: string;
};
export type SubjectiveContentNotYetType = {
  subjectiveFormId: number;
  question: string;
};

// Thinking types

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
