'use client'
import axios from 'axios'

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const axiosApi = (url: string, data?: any) => {


    const instance = axios.create({
        baseURL: url,
        withCredentials: true,
        ...data,
    });

    return instance;
};

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const unAxiosApi = (url: string, data?: any) => {
    const instance = axios.create({
        baseURL: url,
        withCredentials: true,
        ...data,
    });

    return instance;
};

export const defaultInstance = axiosApi(
    'http://54.180.90.236:8080/swagger-ui/index.html',
);