"use client"

import { useGetSurveyQuery } from "@/api/survey-api";
import { mainColor } from "@/components/Themes/color";
import { ISurvey } from "@/components/types/dto";
import { Checkbox, FormControlLabel, Radio, RadioGroup, Stack, TextField } from "@mui/material"
import React, { useEffect, useState } from "react";
import { SurveyBox } from "./SurveyId.style";
import RadioLabel from "@/components/SurveyItems/SurveyRadioBox";
import Button from "@/components/Button/Button";
import PageLink from "@/components/PageLink/PageLink";

function Page({ params }: { params: { surveyId: number } }) {
    const [value, setValue] = React.useState('');

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setValue((event.target as HTMLInputElement).value);
    };

    //get main survey contents
    const { data: surveyData } = useGetSurveyQuery({
        surveyId: params.surveyId,
    });
    const [survey, setSurvey] = useState<ISurvey>()
    useEffect(() => {
        setSurvey(surveyData);
    }, [surveyData])
    return (
        <Stack margin={'0 auto'} maxWidth={'1024px'}>
            <Stack
                fontSize={"70px"}
                color={`${mainColor}`}
                textAlign={"center"}
                margin={"50px 0 200px 0"}
            >설문조사 주제 / 제목</Stack>

            <SurveyBox>
                <Stack display={'flex'} justifyContent={'flex-start'} alignItems={'flex-end'} flexDirection={'row'} marginBottom={'30px'}>
                    <Stack fontSize={'35px'} marginRight={'10px'} color={mainColor}>Q.</Stack>
                    <Stack fontSize={'25px'}>thinking은 괜찮은가?</Stack>
                </Stack>
                <Stack>
                    <RadioGroup
                        value={value}
                        onChange={handleChange}>
                        <FormControlLabel value="0" control={<Radio />} label="very good" />
                        <FormControlLabel value="1" control={<Radio />} label="good" />
                        <FormControlLabel value="2" control={<Radio />} label="bad" />
                    </RadioGroup>
                </Stack>
            </SurveyBox>
            <SurveyBox>
                <Stack display={'flex'} justifyContent={'flex-start'} alignItems={'flex-end'} flexDirection={'row'} marginBottom={'30px'}>
                    <Stack fontSize={'35px'} marginRight={'10px'} color={mainColor}>Q.</Stack>
                    <Stack fontSize={'25px'}>thinking은 왜 괜찮은가?</Stack>
                </Stack>
                <TextField fullWidth></TextField>
            </SurveyBox>
            <Stack display='flex' alignItems={'flex-end'}>
                <Button sx={{ padding: '10px 20px' }}>
                    <PageLink href={"/survey"}>{'제출하기 >>>'}</PageLink>
                </Button>
            </Stack>
        </Stack>
    )
}
export default Page;