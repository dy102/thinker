import { useGetThinkingQuery } from "@/api/thinking-api";
import { IThinking } from "@/components/types/dto";
import { Stack } from "@mui/material";
import { useEffect, useState } from "react";

function NormalThinkingCollect({ kind }: { kind: "recent" | 'like' | 'view' }) {
    const [kindToLastId, setKindToLastId] = useState(kind);

    // normalThinkingData 반환 전 lastId에 null 주입
    const [lastId, setLastId] = useState<number | null>(null);
    if (!lastId) {
        setLastId(null)
    }
    const { data: normalThinkingData } = useGetThinkingQuery({ kind: kind, size: 8, lastId: lastId })
    const [normalThinking, setNormalThinking] = useState<IThinking>()
    //
    if (kindToLastId === 'recent') {
        setLastId(normalThinking?.nextCursor ?? 0)
    }

    useEffect(() => {
        setKindToLastId(kind);
        setNormalThinking(normalThinkingData)
    }, [kind, normalThinkingData])

    return (
        <Stack></Stack>
    );
}
export default NormalThinkingCollect;