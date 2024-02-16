import { defaultInstance } from "@/api";
import {
  ISurveyNormalParams,
  ISurveyPremiumParams,
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
