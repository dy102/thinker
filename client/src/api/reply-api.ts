import { useQuery } from "@tanstack/react-query";
import { defaultInstance } from "@/api";
import { IRepliesParams, IReply } from "@/components/types/dto";

const GetRepliesApi = async (params: IRepliesParams) => {
  const { data } = await defaultInstance.get("/replies", { params });

  return data;
};

export const useGetRepliesQuery = (params: IRepliesParams) => {
  const { isLoading, error, data } = useQuery(["replies", params], () =>
    GetRepliesApi(params)
  );
  return { data, isLoading, error };
};

export const PostReplyApi = async (body: IReply) => {
  const { data } = await defaultInstance.post("/replies", body);

  return data;
};
