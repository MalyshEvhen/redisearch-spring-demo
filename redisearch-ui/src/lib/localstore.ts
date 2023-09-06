import { localStorageStore } from "@skeletonlabs/skeleton";
import type { Writable } from "svelte/store";


type Article = {
    title: string;
    content: string;
    tags: string[];
    authorId: string;
}

export const articleStore: Writable<Article[]> = localStorageStore('articles', []);
