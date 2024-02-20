// simple user 요청값
export interface IMemberSimpleParams {
  memberId: number;
}

// simple user 반환값
export interface IMemberSimple {
  memberName: string;
  memberProfile: [string];
  memberGrade: string;
}

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

// thinking 보기 반환값
export interface IThinking {
  isManager: boolean;
  ThinkingDetailDto: {
    thinkingId: number;
    thinkingImage: string[] | null;
    thinkingWriter: string;
    thinkingTitle: string;
    thinkingContents: string;
    thinkingDate: string;
    isPremium: boolean;
    likeCount: number;
    repliesCount: number;
    viewCount: number;
  };
}

// replies 요청값
export interface IRepliesParams {
  thinkingId: number;
}

interface ReplyDto {
  thinkingId: number;
  replyId: number;
  userId: number;
  replyContents: string;
  likeCount: number;
  createdAt: string;
  isLiked: boolean;
  who: string; //"author" | "me(author)" | "me" | "other"
}

// replies 반환값
export interface IReplies {
  isManager: boolean;
  ReplyDtos: ReplyDto[];
}

// reply post
export interface IReply {
  thinkingId: number;
  replyContent: string;
}

// premium survey 요청값
export interface ISurveyPremiumParams {
  page: number;
}

interface SurveyDto {
  surveyId: number;
  surveyImage: string;
  surveyWriter: string;
  surveyTitle: string;
  surveyItemCount: number;
  isDone: boolean;
  isPremium: boolean;
}

// premium survey 반환값
export interface ISurveyPremium {
  premiumSurveysCount: number;
  SurveyDtos: SurveyDto[];
}

// normal survey 요청값
export interface ISurveyNormalParams {
  lastId: number | null;
  kind: string;
}

// normal survey 반환값
export interface ISurveyNormal {
  surveyDtos: SurveyDto[];
  nextCursor: number;
}


// survey 요청값
export interface ISurveyParams {
  surveyId: number;
}

interface multipleChoiceDto {
  multipleChoiceId: number;
  question: string;
  items: {
    itemId: number;
    item: string;
    isCheck: boolean;
  }[];
}

interface subjectiveDto {
  subjectiveId: number;
  question: string;
  answer: string;
}

// survey 반환값
export interface ISurvey {
  isDone: boolean;
  isManager: boolean;
  surveyDto: {
    surveyId: number;
    multipleChoiceDto: multipleChoiceDto;
    subjectiveDto: subjectiveDto;
  };
};