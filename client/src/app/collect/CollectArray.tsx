"use client";

// import IconButton from "@/components/Button/IconButton";
// import Toast from "@/components/Toast/Toast";
// import { KeyboardArrowLeft } from "@mui/icons-material";
import { Stack } from "@mui/material";
// import { useState } from "react";

function CollectArray({ kind }: { kind: "servey" | "thinking" }) {
  const premiumSurveysResponse = {
    premiumSurveysCount: 3,
    SurveyDtos: [
      {
        surveyId: 1,
        surveyImage: "string",
        surveyWriter: "jsjsjsjs",
        surveyTitle: "Is Thinker good?",
        surveyItemCount: 5,
        isDone: false,
        isPremium: true,
      },
      {
        surveyId: 1,
        surveyImage: "string",
        surveyWriter: "jsjsjsjs",
        surveyTitle: "Is Thinker good?",
        surveyItemCount: 5,
        isDone: false,
        isPremium: true,
      },
    ],
  };

  //   const [page, setPage] = useState(0);
  //   const [backendSendPage, setBackendSendPage] = useState(1);
  //   const [toastOpen, setToastOpen] = useState(false);

  //   const { data } = useGetCollectDataQuery({ kind, page: backendSendPage });
  //   const [kindArray, setKindArray] = useState(0);

  //   const newDataButtonClick = () => {
  //     if (page < kindArray.totalPages - 1) {
  //       setPage((prevPage) => prevPage + 1);
  //       setBackendSendPage((prevPage) => prevPage + 1);
  //     } else {
  //       setToastOpen(true);
  //     }
  //   };

  return (
    <Stack direction="row" justifyContent="left" alignItems="center">
      {/* <Toast
        open={toastOpen}
        onClose={() => setToastOpen(false)}
        toastMessage="게시글이 존재하지 않습니다."
      />
      <IconButton
        onClick={() => {
          page > 0 ? setPage((prevPage) => prevPage - 1) : setToastOpen(true);
        }}>
        <KeyboardArrowLeft />
      </IconButton>
      {

      } */}
    </Stack>
  );
}
