import type { PageLoad } from './$types';

export const load = (async ({ fetch }) => {

    type Article = {
        id: string;
        title: string;
        content: string;
        tags: string[];
    }

    const articlesRes = await fetch('http://localhost:8080/api/articles/all?size=5&page=0',
        {
            mode: 'no-cors',
            method: 'GET',
            headers: {
                "Content-Type": "application/json"
            },
        })
    const articlesData = await articlesRes.json()
    const articles: Article[] = articlesData.content

    return {
        articles: articles
    };
}) satisfies PageLoad;