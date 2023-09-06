<script lang="ts">
    let data: any
	const handleClick = async () => {
		data = await fetch('http://localhost:8080/api/articles/get-by-id/01H9MEHDAXJVM8TPKKWZ5KH2AY',
        {
            mode: 'no-cors',
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        }).then((x) => x.json());
	};
</script>

<button on:click={handleClick}> Click to Load Data </button>
<pre>
  {JSON.stringify(data, null, 2)}
</pre>
