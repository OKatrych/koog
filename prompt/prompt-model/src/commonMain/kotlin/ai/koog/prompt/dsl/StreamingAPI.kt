package ai.koog.prompt.dsl

import ai.koog.prompt.message.ResponseMetaInfo
import kotlinx.serialization.Serializable

/**
 * Represents a streamed assistant message exchanged in a chat with LLM.
 *
 * This sealed interface defines the possible results that can be received when streaming
 * responses from a language model. Streaming allows for real-time processing of responses
 * as they are generated, rather than waiting for the complete response.
 */
@Serializable
public sealed interface StreamingResult {
    /**
     * Represents a chunk of streamed content from the assistant.
     *
     * This is emitted during the streaming process as partial content becomes available.
     * Multiple chunks may be received for a single response, and they should be concatenated
     * to form the complete message content.
     *
     * @property content The partial text content received in this streaming chunk.
     */
    @Serializable
    public data class Chunk(
        val content: String,
    ) : StreamingResult

    /**
     * Represents the completion of a streamed response.
     *
     * This is emitted as the final result when the streaming process completes,
     * indicating that no more chunks will be received for the current response.
     *
     * @property finishReason An optional explanation for why the assistant's response was finalized.
     * Defaults to null if not provided.
     * @property metaInfo Metadata related to the response, including token counts and timestamp.
     */
    @Serializable
    public data class Finish(
        val finishReason: String?,
        val metaInfo: ResponseMetaInfo,
    ) : StreamingResult
}
