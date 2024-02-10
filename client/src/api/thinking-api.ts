import { useQuery } from "@tanstack/react-query";
import { defaultInstance } from "@/api";
import { IThinkingParams, IThinkingPremiumParams } from "@/components/types/dto";

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
const GetThinkingApi = async (params: IThinkingParams) => {
  const { data } = await defaultInstance.get(`/thinking/${params.kind}`, { params });

  return data;
}

export const useGetThinkingQuery = (params: IThinkingParams) => {
  const { isLoading, error, data } = useQuery([`thinking`, params], () => GetThinkingApi(params)
  );
  return { data, isLoading, error };
};