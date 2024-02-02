import { IconButton, Stack } from "@mui/material";
import Toast from "../Toast/Toast";
import { useEffect, useState } from "react";
import { IThinkingPremium } from "../types/dto";
import { useGetThinkingPremiumQuery } from "@/api/thinking-api";
import { KeyboardArrowLeft, KeyboardArrowRight } from "@mui/icons-material";
import PremiumThinking from "../Premium/PremiumThinking/PremiumThinking";

export default function PremiumThinkingCollect() {

    const premiumThinkingResponse = {
        premiumThinkingCount: 2,
        dtos: [
            {
                thinkingId: 4,
                thinkingThumbnail:
                    "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
                thinkingWriter: "Jun Seo",
                thinkingTitle: "Is Thinker good?",
                isPremium: true,
                likeCount: 5,
                repliesCount: 10,
                viewCount: 123,
            },
            {
                thinkingId: 5,
                thinkingThumbnail:
                    "https://t4.ftcdn.net/jpg/00/93/83/93/240_F_93839373_JT5cpdRmTyuOg6eert4Kexq8aO63iD5r.jpg",
                thinkingWriter: "Jun Seo",
                thinkingTitle: "Is Thinker good?",
                isPremium: true,
                likeCount: 5,
                repliesCount: 10,
                viewCount: 123,
            },
            {
                thinkingId: 6,
                thinkingThumbnail:
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS3l2fb-6xT3Krier-wokMNXGNpM-t6qvSghg&usqp=CAU",
                thinkingWriter: "Jun Seo",
                thinkingTitle: "Is Thinker good?",
                isPremium: true,
                likeCount: 5,
                repliesCount: 10,
                viewCount: 123,
            },
        ],
    };
    const [page, setPage] = useState(0);
    const [backendSendPage, setBackendSendPage] = useState(1);
    const [toastOpen, setToastOpen] = useState(false);

    const [thinkingPremium, setThinkingPremium] = useState<IThinkingPremium>();
    const { data: thinkingPremiumData } = useGetThinkingPremiumQuery({ page: backendSendPage });
    const newDataButtonClick = () => {
        if (page < (thinkingPremium?.premiumThinkingCount ?? 0)) {
            setPage((prevPage) => prevPage + 1);
            setBackendSendPage((prevPage) => prevPage + 1);
        } else {
            setToastOpen(true);
        }
    }
    useEffect(() => {
        setThinkingPremium(thinkingPremiumData);
    }, [thinkingPremiumData]);
    return (
        <Stack flexDirection={"row"}>
            <Toast
                open={toastOpen}
                onClose={() => setToastOpen(false)}
                toastMessage="게시글이 존재하지 않습니다."
            />
            <IconButton
                sx={{ width: '32px', height: '32px', margin: 'auto' }}
                onClick={() => {
                    page > 0 ? setPage((prevPage) => prevPage - 1) : setToastOpen(true);
                }}>
                <KeyboardArrowLeft />
            </IconButton>
            {premiumThinkingResponse?.dtos?.map((thinking) => {
                return (
                    <PremiumThinking
                        key={thinking.thinkingId}
                        thinkingThumbnail={thinking.thinkingThumbnail}
                        thinkingWriter={thinking.thinkingWriter}
                        thinkingTitle={thinking.thinkingTitle}
                        likecount={thinking.likeCount}
                        repliesCount={thinking.repliesCount}
                        viewCount={thinking.viewCount}
                    />
                );
            })}
            <IconButton sx={{ width: '32px', height: '32px', margin: 'auto' }} onClick={newDataButtonClick}>
                <KeyboardArrowRight />
            </IconButton>
        </Stack>
    )
}