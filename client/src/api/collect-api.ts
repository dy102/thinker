import { useQuery } from "@tanstack/react-query";
import { defaultInstance } from "@/api";
import { IThinkingPremium } from "@/components/types/dto";

const GetThinkingPremiumApi = async (params: IThinkingPremium) => {
  const { data } = await defaultInstance.get(`/thinking/premium`, { params });

  return data;
};

export const useGetThinkingPremiumQuery = (params: IThinkingPremium) => {
  const { isLoading, error, data } = useQuery([`thinkingPremium`, params], () =>
    GetThinkingPremiumApi(params)
  );
  return { data, isLoading, error };
};
