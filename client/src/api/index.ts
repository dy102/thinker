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
  "http://3.34.46.79:8081/swagger-ui/index.html#/"
);
