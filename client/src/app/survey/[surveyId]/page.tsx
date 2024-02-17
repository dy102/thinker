"use client"

import { useGetSurveyQuery } from "@/api/survey-api";
import { ISurvey } from "@/components/types/dto";
import { Stack } from "@mui/material"
import { useState } from "react";

function Page({ params }: { params: { surveyId: number } }) {

    //get main survey contents
    const { data: surveyData } = useGetSurveyQuery({
        surveyId: params.surveyId,
    });
    const [survey, setSurvey] = useState<ISurvey>()
    return (
        <Stack>

        </Stack>
    )
}
export default Page;