"use client";
import { defaultInstance } from "@/api";
import { IThinkingPremium } from "@/components/types/dto";
import { Stack } from "@mui/material";
import { useQuery } from "@tanstack/react-query";
import { useState } from "react";

function ThinkingPremiumArray() {
  const [page, setPage] = useState(0);

  const GetCollectDataApi = async (params: IThinkingPremium) => {
    const { data } = await defaultInstance.get("/thinking/premium", { params });

    return data;
  };
  const useGetCollectDataQuery = (params: IThinkingPremium) => {
    const { isLoading, error, data } = useQuery(
      ["thinkingPremium", params],
      () => GetCollectDataApi(params)
    );
    return { data, isLoading, error };
  };

  const [toastOpen, setToastOpen] = useState(false);

  return <Stack></Stack>;
}

export default ThinkingPremiumArray;
