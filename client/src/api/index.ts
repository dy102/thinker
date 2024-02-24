"use client";
import axios from "axios";

const axiosApi = (url: string, data?: any) => {
  const instance = axios.create({
    baseURL: url,
    withCredentials: true,
    ...data,
  });

  return instance;
};

export const defaultInstance = axiosApi(
  "http://43.200.182.88:8081/swagger-ui/index.html#/"
);
