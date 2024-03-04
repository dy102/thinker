import { defaultInstance } from "@/api";
import {
  IMakeSurveys,
  IMultipleChoice,
  ISubjective,
  ISurveyNormalParams,
  ISurveyParams,
  ISurveyPremiumParams,
  ISurveysParticipate,
} from "@/components/types/dto";
import { useQuery } from "@tanstack/react-query";

// premium survey get
const GetSurveyPremiumApi = async (params: ISurveyPremiumParams) => {
  const { data } = await defaultInstance.get("/surveys/premium", { params });

  return data;
};

export const useGetSurveyPremiumQuery = (params: ISurveyPremiumParams) => {
  const { isLoading, error, data } = useQuery(["surveyPremium", params], () =>
    GetSurveyPremiumApi(params)
  );
  return { isLoading, error, data };
};

// normal survey get
const GetSurveyNormalApi = async (params: ISurveyNormalParams) => {
  const { data } = await defaultInstance.get(`/thinking/${params.kind}`, {
    params,
  });
  return data;
};

export const useGetSurveyNormalQuery = (params: ISurveyNormalParams) => {
  const { isLoading, error, data } = useQuery(["surveyNormal", params], () =>
    GetSurveyNormalApi(params)
  );
  return { data, isLoading, error };
};

// get survey
const GetSurveyApi = async (params: ISurveyParams) => {
  const { data } = await defaultInstance.get("/surveys", { params });

  return data;
};

export const useGetSurveyQuery = (params: ISurveyParams) => {
  const { isLoading, error, data } = useQuery(["surveys", params], () =>
    GetSurveyApi(params)
  );
  return { data, isLoading, error };
};

// multipleChoice survey post
export const PostMultipleChoiceApi = async (body: IMultipleChoice) => {
  const { data } = await defaultInstance.post("/surveys/multiple", body);

  return data;
};

// subjective survey post
export const PostSubjectiveApi = async (body: ISubjective) => {
  const { data } = await defaultInstance.post("/surveys/subjective", body);

  return data;
};

// when surveys participate
export const PostSurveysParticipate = async (body: ISurveysParticipate) => {
  const { data } = await defaultInstance.post("/surveys/participate", body);

  return data;
};

// make survey post
export const PostMakeSurveys = async (body: IMakeSurveys) => {
  const { data } = await defaultInstance.post("surveys/make", body);

  return data
}