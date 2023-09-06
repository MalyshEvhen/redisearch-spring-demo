<script lang="ts">
    import { goto } from "$app/navigation";
    import { articleStore } from "$lib/localstore";
    import { InputChip, getToastStore, type ToastSettings } from "@skeletonlabs/skeleton";

    let tags: string[] = [];
    let title: string;
    let content: string;
    let authorId: string;

    const toastStore = getToastStore();

    const t: ToastSettings = {
        message: 'Article created successfully',
        background: 'variant-filled-success'
    }

    function createArticle(): void {
        articleStore.update((articles) => [
            ...articles,
            {
                title,
                content,
                tags,
                authorId,
            }
        ]);
        title = '';
        content = '';
        tags = [];
        authorId = '';
        toastStore.trigger(t);
        goto('/');
    }
</script>

<div class="container h-full mx-auto justify-center gap-8 flex flex-col">
    <form class="card p-4 flex flex-col gap-3">
        <h2>New Article</h2>
        <textarea
            bind:value={title}
            class="textarea"
            rows="2"
            placeholder="Title..."
        />
        <textarea
            bind:value={content}
            class="textarea"
            rows="10"
            placeholder="Content..."
        />
        <textarea
            bind:value={authorId}
            class="textarea"
            rows="1"
            placeholder="Authors ID..."
        />
        <InputChip bind:value={tags} name="tags" placeholder="Tags..." />
        <button
            type="button"
            on:click={createArticle}
            class="btn varint-ghost-primary elf-end">Post</button
        >
    </form>
</div>
