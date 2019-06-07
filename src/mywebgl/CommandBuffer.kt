package mywebgl

/**
 * Buffer that stores commands
 * each command contains a command code(int) and an item containing command data,
 * such us a rectangle for masking, or a displayobject for geometry rendering
 *
 */

class CommandBuffer {

    private val commands: Array<Int> = js("[]")
    private val item: Array<dynamic> = js("[]")
    private var totalCommands: Int = 0
    private var counter: Int = 0

    fun clear() {
        totalCommands = 0
        counter = 0
    }

    fun consume() {
        counter++
    }

    fun consume(action: (Int, dynamic) -> Unit) {
        action(commands[counter], item[counter])
        counter++
    }

    fun consumeAll(action: (Int, dynamic) -> Unit) {
        for (i in counter until totalCommands) action(commands[i], item[i])
        counter = totalCommands
    }

    val nextCommand: Int get() = commands[counter]
    val nextItem: dynamic get() = item[counter]

    fun add(command: Int, _item: dynamic) {
        commands[totalCommands] = command
        item[totalCommands] = _item
        totalCommands++
    }

    val size get() = totalCommands - counter
}

/**
 * array of command buffers
 */

class CommandBufferGroup {

    val commandGroups: Array<CommandBuffer> = js("[]")
    private var totalGroups: Int = 0

    fun clear() {
        totalGroups = 0
    }

    fun add(buffer: CommandBuffer) {
        commandGroups[totalGroups] = buffer
        totalGroups++
    }

    val size get() = totalGroups
}



/**
 * pool that serves command buffers
 */

class CommandBufferPool{

    private var bufferGroups = js("{}")
    private val buffers: Array<CommandBuffer> = js("[]")
    private var totalBuffers: Int = 0
    private var counter: Int = 0

    fun clear(){
        bufferGroups = js("{}")
        counter=0
    }

    fun hasGroup(groupId:String) = bufferGroups[groupId]!=undefined
    fun getBufferGroup(groupId: String): CommandBufferGroup {

      return  bufferGroups[groupId]
    }

    fun get(groupId: String = ""): CommandBuffer {

        val newBuffer = if (counter==totalBuffers) {
            val newBuffer = CommandBuffer()
            buffers[counter]=newBuffer
            counter++
            totalBuffers++
            newBuffer
        } else{
            val buffer = buffers[counter]
            buffer.clear()
            counter++
            buffer
        }
        if (groupId.isNotEmpty()){
            if (bufferGroups[groupId]==undefined) bufferGroups[groupId]= CommandBufferGroup()
            val asd: CommandBufferGroup = bufferGroups[groupId]
            asd.add(newBuffer)
        }

        return newBuffer
    }
}


