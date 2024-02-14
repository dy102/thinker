import { useQuery } from "@tanstack/react-query";
import { defaultInstance } from "@/api";
import { IMemberSimpleParams } from "@/components/types/dto";

const GetMemberSimpleApi = async (params: IMemberSimpleParams) => {
  const { data } = await defaultInstance.get(`/members/simple`, { params });

  return data;
};

export const useGetMemberSimpleQuery = (params: IMemberSimpleParams) => {
  const { isLoading, error, data } = useQuery([`memberSimple`, params], () =>
    GetMemberSimpleApi(params)
  );
  return { data, isLoading, error };
};
