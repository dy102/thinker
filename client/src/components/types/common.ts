// Modal Type
export type ModalType = {
  open: boolean;
  onClose: () => void;
};

// PremiumSurvey Type
export type PremiumSurveyType = {
  surveyImg: string;
  surveyWriter: string;
  surveyTitle: string;
  surveyItemCount: number;
  isDone: boolean;
};

// PremiumThinking Type
export type PremiumThinkingType = {
  thinkingImg: string;
  thinkingWriter: string;
  thinkingTitle: string;
  likeCount: number;
  viewCount: number;
};
