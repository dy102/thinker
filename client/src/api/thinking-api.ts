import { useQuery } from "@tanstack/react-query";
import { defaultInstance } from "@/api";
import {
  IThinkingNormalParams,
  IThinkingParams,
  IThinkingPremiumParams,
} from "@/components/types/dto";

// primium thinking get
const GetThinkingPremiumApi = async (params: IThinkingPremiumParams) => {
  const { data } = await defaultInstance.get(`/thinking/premium`, { params });

  return data;
};

export const useGetThinkingPremiumQuery = (params: IThinkingPremiumParams) => {
  const { isLoading, error, data } = useQuery([`thinkingPremium`, params], () =>
    GetThinkingPremiumApi(params)
  );
  return { data, isLoading, error };
};

// normal thinking get
const GetThinkingNormalApi = async (params: IThinkingNormalParams) => {
  const { data } = await defaultInstance.get(`/thinking/${params.kind}`, {
    params,
  });

  return data;
};

export const useGetThinkingNormalQuery = (params: IThinkingNormalParams) => {
  const { isLoading, error, data } = useQuery([`thinkingNormal`, params], () =>
    GetThinkingNormalApi(params)
  );
  return { data, isLoading, error };
};

// get thinking
const GetThinkingApi = async (params: IThinkingParams) => {
  const { data } = await defaultInstance.get(`/thinking`, { params });

  return data;
};

export const useGetThinkingQuery = (params: IThinkingParams) => {
  const { isLoading, error, data } = useQuery(["thinking", params], () =>
    GetThinkingApi(params)
  );
  return { data, isLoading, error };
};
