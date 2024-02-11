import { Stack } from "@mui/material";

export default function PremiumSurveyCollect() {
    const premiumSurveysResponse = {
        premiumSurveysCount: 2,
        SurveyDtos: [
            {
                surveyId: 1,
                surveyImage:
                    "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
                surveyWriter: "Jun Seo",
                surveyTitle: "Is Thinker good?",
                surveyItemCount: 5,
                isDone: true,
                isPremium: true,
            },
            {
                surveyId: 2,
                surveyImage:
                    "https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg",
                surveyWriter: "Do Yeon",
                surveyTitle: "Is Thinker good? Is Thinker good? Is Thinker good?",
                surveyItemCount: 12,
                isDone: false,
                isPremium: true,
            },
            {
                surveyId: 3,
                surveyImage:
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3l2fb-6xT3Krier-wokMNXGNpM-t6qvSghg&usqp=CAU",
                surveyWriter: "Walt Disney",
                surveyTitle: "Disney Plus Subscribe prise",
                surveyItemCount: 12,
                isDone: false,
                isPremium: true,
            },
        ],
    };
    return (
        <Stack></Stack>
    )
}