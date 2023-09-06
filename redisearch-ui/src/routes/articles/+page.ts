import type { PageLoad } from './$types';

export const load = (async ({ fetch }) => {
    const articlesRes = await fetch('http://localhost:8080/api/articles/all?size=5&page=0')
    const articlesData = await articlesRes.json()
    const articles = articlesData.content

    return {
        articles: articles
    };
}) satisfies PageLoad;