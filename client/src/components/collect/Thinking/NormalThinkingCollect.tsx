import { useGetThinkingNormalQuery } from "@/api/thinking-api";
import { IThinkingNormal } from "@/components/types/dto";
import { Stack } from "@mui/material";
import { useEffect, useState } from "react";
import NormalThinking from "../../Normal/NormalThinking/NormalThinking";

function NormalThinkingCollect({ kind }: { kind: string }) {
  const normalThinkingResponse = {
    contents: {
      dtos: [
        {
          thinkingId: 14,
          thinkingThumbnail: [
            "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
          ],
          thinkingWriter: "Jun Seo",
          thinkingTitle: "Is Thinker good?",
          isPremium: false,
          likeCount: 5,
          repliesCount: 10,
          viewCount: 123,
        },
        {
          thinkingId: 15,
          thinkingThumbnail: [null],
          thinkingWriter: "Jun Seo",
          thinkingTitle: "Is Thinker good?",
          isPremium: false,
          likeCount: 5,
          repliesCount: 10,
          viewCount: 123,
        },
        {
          thinkingId: 16,
          thinkingThumbnail: [
            "https://assets.paintingco.com/dev-3/wp-content/uploads/20230802110655/shutterstock_1561397020-scaled.jpg",
          ],
          thinkingWriter: "Jun Seo",
          thinkingTitle: "New Netflix Subscription System",
          isPremium: false,
          likeCount: 5,
          repliesCount: 10,
          viewCount: 123,
        },
      ],
    },
    totalElements: 0,
    nextCursor: 0,
  };

  const [kindToLastId, setKindToLastId] = useState(kind);

  // normalThinkingData 반환 전 lastId에 null 주입
  const [lastId, setLastId] = useState<number | null>(0);
  const { data: normalThinkingData } = useGetThinkingNormalQuery({
    kind: kind,
    size: 8,
    lastId: lastId,
  });
  const [normalThinking, setNormalThinking] = useState<IThinkingNormal>();

  useEffect(() => {
    setKindToLastId(kind);
    setNormalThinking(normalThinkingData);
    if (kindToLastId === "recent") {
      setLastId(normalThinking?.nextCursor ?? 0);
    }
  }, [kind, normalThinkingData, kindToLastId, normalThinking?.nextCursor]);

  return (
    <Stack
      width={"1084px"}
      height={"100%"}
      display={"flex"}
      flexWrap={"wrap"}
      flexDirection={"row"}
    >
      {normalThinkingResponse?.contents.dtos.map((normal) => {
        return (
          <NormalThinking
            key={normal.thinkingId}
            thinkingId={normal.thinkingId}
            thinkingThumbnail={normal.thinkingThumbnail[0]}
            thinkingWriter={normal.thinkingWriter}
            thinkingTitle={normal.thinkingTitle}
            likecount={normal.likeCount}
            repliesCount={normal.repliesCount}
            viewCount={normal.viewCount}
          />
        );
      })}
    </Stack>
  );
}
export default NormalThinkingCollect;
