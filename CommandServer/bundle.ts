/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
namespace acme.crypto {
    export class Base64 {
        public static encode(data : number[]) : string {
            let tbl : string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'];
            let buffer : { str: string, toString: Function } = { str: "", toString: function() { return this.str; } };
            let pad : number = 0;
            for(let i : number = 0; i < data.length; i += 3) {{
                let b : number = ((data[i] & 255) << 16) & 16777215;
                if(i + 1 < data.length) {
                    b |= (data[i + 1] & 255) << 8;
                } else {
                    pad++;
                }
                if(i + 2 < data.length) {
                    b |= (data[i + 2] & 255);
                } else {
                    pad++;
                }
                for(let j : number = 0; j < 4 - pad; j++) {{
                    let c : number = (b & 16515072) >> 18;
                    /* append */(sb => { sb.str = sb.str.concat(<any>tbl[c]); return sb; })(buffer);
                    b <<= 6;
                };}
            };}
            for(let j : number = 0; j < pad; j++) {{
                /* append */(sb => { sb.str = sb.str.concat(<any>"="); return sb; })(buffer);
            };}
            return /* toString */buffer.str;
        }

        public static decode(data : string) : number[] {
            let tbl : number[] = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1];
            let bytes : number[] = /* getBytes */(data).split('').map(s => s.charCodeAt(0));
            let len : number = data.length * 6;
            if((data.length > 1) && ((c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(data.charAt(data.length - 2)) == '='.charCodeAt(0))) {
                len = len - 6;
            }
            if((data.length > 0) && ((c => c.charCodeAt==null?<any>c:c.charCodeAt(0))(data.charAt(data.length - 1)) == '='.charCodeAt(0))) {
                len = len - 6;
            }
            let buffer : number[] = (s => { let a=[]; while(s-->0) a.push(0); return a; })((len / 8|0));
            let bufferIndex : number = 0;
            for(let i : number = 0; i < bytes.length; ) {{
                let b : number = 0;
                if(tbl[bytes[i]] !== -1) {
                    b = (tbl[bytes[i]] & 255) << 18;
                } else {
                    i++;
                    continue;
                }
                let num : number = 0;
                if(i + 1 < bytes.length && tbl[bytes[i + 1]] !== -1) {
                    b = b | ((tbl[bytes[i + 1]] & 255) << 12);
                    num++;
                }
                if(i + 2 < bytes.length && tbl[bytes[i + 2]] !== -1) {
                    b = b | ((tbl[bytes[i + 2]] & 255) << 6);
                    num++;
                }
                if(i + 3 < bytes.length && tbl[bytes[i + 3]] !== -1) {
                    b = b | (tbl[bytes[i + 3]] & 255);
                    num++;
                }
                while((num > 0)) {{
                    let c : number = (b & 16711680) >> 16;
                    buffer[bufferIndex++] = (<number>c|0);
                    b <<= 8;
                    num--;
                }};
                i += 4;
            };}
            return buffer;
        }
    }
    Base64["__class"] = "acme.crypto.Base64";

}
namespace acme.crypto {
    export class CryptoUtils {
        public static zeroBlock$byte_A$int$int(block : number[], off : number, len : number) {
            for(let i : number = off; i < off + len; ++i) {block[i] = 0;}
        }

        public static zeroBlock(block? : any, off? : any, len? : any) : any {
            if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && ((typeof off === 'number') || off === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.zeroBlock$byte_A$int$int(block, off, len);
            } else if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && off === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.zeroBlock$byte_A(block);
            } else throw new Error('invalid overload');
        }

        public static zeroBlock$byte_A(block : number[]) {
            CryptoUtils.zeroBlock$byte_A$int$int(block, 0, block.length);
        }

        public static randomBlock$byte_A$int$int(block : number[], off : number, len : number) {
            for(let i : number = off; i < off + len; ++i) {block[i] = (<number>(Math.random() * 256.0)|0);}
        }

        public static randomBlock(block? : any, off? : any, len? : any) : any {
            if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && ((typeof off === 'number') || off === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.randomBlock$byte_A$int$int(block, off, len);
            } else if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && off === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.randomBlock$byte_A(block);
            } else throw new Error('invalid overload');
        }

        public static randomBlock$byte_A(block : number[]) {
            CryptoUtils.randomBlock$byte_A$int$int(block, 0, block.length);
        }

        public static xorBlock$byte_A$int$byte_A$int$byte_A$int$int(a : number[], aOff : number, b : number[], bOff : number, dst : number[], dstOff : number, len : number) {
            for(let i : number = 0; i < len; ++i) {dst[dstOff + i] = (<number>(a[aOff + i] ^ b[bOff + i])|0);}
        }

        public static xorBlock(a? : any, aOff? : any, b? : any, bOff? : any, dst? : any, dstOff? : any, len? : any) : any {
            if(((a != null && a instanceof <any>Array && (a.length==0 || a[0] == null ||(typeof a[0] === 'number'))) || a === null) && ((typeof aOff === 'number') || aOff === null) && ((b != null && b instanceof <any>Array && (b.length==0 || b[0] == null ||(typeof b[0] === 'number'))) || b === null) && ((typeof bOff === 'number') || bOff === null) && ((dst != null && dst instanceof <any>Array && (dst.length==0 || dst[0] == null ||(typeof dst[0] === 'number'))) || dst === null) && ((typeof dstOff === 'number') || dstOff === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.xorBlock$byte_A$int$byte_A$int$byte_A$int$int(a, aOff, b, bOff, dst, dstOff, len);
            } else if(((a != null && a instanceof <any>Array && (a.length==0 || a[0] == null ||(typeof a[0] === 'number'))) || a === null) && ((aOff != null && aOff instanceof <any>Array && (aOff.length==0 || aOff[0] == null ||(typeof aOff[0] === 'number'))) || aOff === null) && ((b != null && b instanceof <any>Array && (b.length==0 || b[0] == null ||(typeof b[0] === 'number'))) || b === null) && bOff === undefined && dst === undefined && dstOff === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.xorBlock$byte_A$byte_A$byte_A(a, aOff, b);
            } else throw new Error('invalid overload');
        }

        public static xorBlock$byte_A$byte_A$byte_A(a : number[], b : number[], dst : number[]) {
            CryptoUtils.xorBlock$byte_A$int$byte_A$int$byte_A$int$int(a, 0, b, 0, dst, 0, a.length);
        }

        public static copyBlock$byte_A$int$byte_A$int$int(src : number[], srcOff : number, dst : number[], dstOff : number, len : number) {
            for(let i : number = 0; i < len; ++i) {dst[dstOff + i] = src[srcOff + i];}
        }

        public static copyBlock(src? : any, srcOff? : any, dst? : any, dstOff? : any, len? : any) : any {
            if(((src != null && src instanceof <any>Array && (src.length==0 || src[0] == null ||(typeof src[0] === 'number'))) || src === null) && ((typeof srcOff === 'number') || srcOff === null) && ((dst != null && dst instanceof <any>Array && (dst.length==0 || dst[0] == null ||(typeof dst[0] === 'number'))) || dst === null) && ((typeof dstOff === 'number') || dstOff === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.copyBlock$byte_A$int$byte_A$int$int(src, srcOff, dst, dstOff, len);
            } else if(((src != null && src instanceof <any>Array && (src.length==0 || src[0] == null ||(typeof src[0] === 'number'))) || src === null) && ((srcOff != null && srcOff instanceof <any>Array && (srcOff.length==0 || srcOff[0] == null ||(typeof srcOff[0] === 'number'))) || srcOff === null) && dst === undefined && dstOff === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.copyBlock$byte_A$byte_A(src, srcOff);
            } else throw new Error('invalid overload');
        }

        public static copyBlock$byte_A$byte_A(src : number[], dst : number[]) {
            CryptoUtils.copyBlock$byte_A$int$byte_A$int$int(src, 0, dst, 0, src.length);
        }

        public static equalsBlock$byte_A$int$byte_A$int$int(a : number[], aOff : number, b : number[], bOff : number, len : number) : boolean {
            for(let i : number = 0; i < len; ++i) {if(a[aOff + i] !== b[bOff + i]) return false;;}
            return true;
        }

        public static equalsBlock(a? : any, aOff? : any, b? : any, bOff? : any, len? : any) : any {
            if(((a != null && a instanceof <any>Array && (a.length==0 || a[0] == null ||(typeof a[0] === 'number'))) || a === null) && ((typeof aOff === 'number') || aOff === null) && ((b != null && b instanceof <any>Array && (b.length==0 || b[0] == null ||(typeof b[0] === 'number'))) || b === null) && ((typeof bOff === 'number') || bOff === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.equalsBlock$byte_A$int$byte_A$int$int(a, aOff, b, bOff, len);
            } else if(((a != null && a instanceof <any>Array && (a.length==0 || a[0] == null ||(typeof a[0] === 'number'))) || a === null) && ((aOff != null && aOff instanceof <any>Array && (aOff.length==0 || aOff[0] == null ||(typeof aOff[0] === 'number'))) || aOff === null) && b === undefined && bOff === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.equalsBlock$byte_A$byte_A(a, aOff);
            } else throw new Error('invalid overload');
        }

        public static equalsBlock$byte_A$byte_A(a : number[], b : number[]) : boolean {
            return CryptoUtils.equalsBlock$byte_A$int$byte_A$int$int(a, 0, b, 0, a.length);
        }

        public static fillBlock$byte_A$int$byte$int(block : number[], blockOff : number, b : number, len : number) {
            for(let i : number = blockOff; i < blockOff + len; ++i) {block[i] = b;}
        }

        public static fillBlock(block? : any, blockOff? : any, b? : any, len? : any) : any {
            if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && ((typeof blockOff === 'number') || blockOff === null) && ((typeof b === 'number') || b === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.fillBlock$byte_A$int$byte$int(block, blockOff, b, len);
            } else if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && ((typeof blockOff === 'number') || blockOff === null) && b === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.fillBlock$byte_A$byte(block, blockOff);
            } else throw new Error('invalid overload');
        }

        public static fillBlock$byte_A$byte(block : number[], b : number) {
            CryptoUtils.fillBlock$byte_A$int$byte$int(block, 0, b, block.length);
        }

        public static squashBytesToInts(inBytes : number[], inOff : number, outInts : number[], outOff : number, intLen : number) {
            for(let i : number = 0; i < intLen; ++i) {outInts[outOff + i] = ((inBytes[inOff + i * 4] & 255) << 24) | ((inBytes[inOff + i * 4 + 1] & 255) << 16) | ((inBytes[inOff + i * 4 + 2] & 255) << 8) | ((inBytes[inOff + i * 4 + 3] & 255));}
        }

        public static spreadIntsToBytes(inInts : number[], inOff : number, outBytes : number[], outOff : number, intLen : number) {
            for(let i : number = 0; i < intLen; ++i) {{
                outBytes[outOff + i * 4] = (<number>((inInts[inOff + i] >>> 24) & 255)|0);
                outBytes[outOff + i * 4 + 1] = (<number>((inInts[inOff + i] >>> 16) & 255)|0);
                outBytes[outOff + i * 4 + 2] = (<number>((inInts[inOff + i] >>> 8) & 255)|0);
                outBytes[outOff + i * 4 + 3] = (<number>((inInts[inOff + i]) & 255)|0);
            };}
        }

        public static squashBytesToIntsLittle(inBytes : number[], inOff : number, outInts : number[], outOff : number, intLen : number) {
            for(let i : number = 0; i < intLen; ++i) {outInts[outOff + i] = ((inBytes[inOff + i * 4] & 255)) | ((inBytes[inOff + i * 4 + 1] & 255) << 8) | ((inBytes[inOff + i * 4 + 2] & 255) << 16) | ((inBytes[inOff + i * 4 + 3] & 255) << 24);}
        }

        public static spreadIntsToBytesLittle(inInts : number[], inOff : number, outBytes : number[], outOff : number, intLen : number) {
            for(let i : number = 0; i < intLen; ++i) {{
                outBytes[outOff + i * 4] = (<number>((inInts[inOff + i]) & 255)|0);
                outBytes[outOff + i * 4 + 1] = (<number>((inInts[inOff + i] >>> 8) & 255)|0);
                outBytes[outOff + i * 4 + 2] = (<number>((inInts[inOff + i] >>> 16) & 255)|0);
                outBytes[outOff + i * 4 + 3] = (<number>((inInts[inOff + i] >>> 24) & 255)|0);
            };}
        }

        public static squashBytesToShorts(inBytes : number[], inOff : number, outShorts : number[], outOff : number, shortLen : number) {
            for(let i : number = 0; i < shortLen; ++i) {outShorts[outOff + i] = ((inBytes[inOff + i * 2] & 255) << 8) | ((inBytes[inOff + i * 2 + 1] & 255));}
        }

        public static spreadShortsToBytes(inShorts : number[], inOff : number, outBytes : number[], outOff : number, shortLen : number) {
            for(let i : number = 0; i < shortLen; ++i) {{
                outBytes[outOff + i * 2] = (<number>((inShorts[inOff + i] >>> 8) & 255)|0);
                outBytes[outOff + i * 2 + 1] = (<number>((inShorts[inOff + i]) & 255)|0);
            };}
        }

        public static squashBytesToShortsLittle(inBytes : number[], inOff : number, outShorts : number[], outOff : number, shortLen : number) {
            for(let i : number = 0; i < shortLen; ++i) {outShorts[outOff + i] = ((inBytes[inOff + i * 2] & 255)) | ((inBytes[inOff + i * 2 + 1] & 255) << 8);}
        }

        public static spreadShortsToBytesLittle(inShorts : number[], inOff : number, outBytes : number[], outOff : number, shortLen : number) {
            for(let i : number = 0; i < shortLen; ++i) {{
                outBytes[outOff + i * 2] = (<number>((inShorts[inOff + i]) & 255)|0);
                outBytes[outOff + i * 2 + 1] = (<number>((inShorts[inOff + i] >>> 8) & 255)|0);
            };}
        }

        public static toStringBlock$byte_A$int$int(block : number[], off : number, len : number) : string {
            let hexits : string = "0123456789abcdef";
            let buf : { str: string, toString: Function } = { str: "", toString: function() { return this.str; } };
            for(let i : number = off; i < off + len; ++i) {{
                /* append */(sb => { sb.str = sb.str.concat(<any>hexits.charAt((block[i] >>> 4) & 15)); return sb; })(buf);
                /* append */(sb => { sb.str = sb.str.concat(<any>hexits.charAt(block[i] & 15)); return sb; })(buf);
            };}
            return "[" + buf + "]";
        }

        public static toStringBlock(block? : any, off? : any, len? : any) : any {
            if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && ((typeof off === 'number') || off === null) && ((typeof len === 'number') || len === null)) {
                return <any>acme.crypto.CryptoUtils.toStringBlock$byte_A$int$int(block, off, len);
            } else if(((block != null && block instanceof <any>Array && (block.length==0 || block[0] == null ||(typeof block[0] === 'number'))) || block === null) && off === undefined && len === undefined) {
                return <any>acme.crypto.CryptoUtils.toStringBlock$byte_A(block);
            } else throw new Error('invalid overload');
        }

        public static toStringBlock$byte_A(block : number[]) : string {
            return CryptoUtils.toStringBlock$byte_A$int$int(block, 0, block.length);
        }
    }
    CryptoUtils["__class"] = "acme.crypto.CryptoUtils";

}
namespace acme.crypto {
    export class SecurityUtil {
        public static makeResponse(challenge : string, password : string) : string {
            let cipher : acme.crypto.IdeaCipher = new acme.crypto.IdeaCipher(password);
            let plain : number[] = /* getBytes */(challenge).split('').map(s => s.charCodeAt(0));
            let len : number = plain.length;
            let padding : number = 8 - (len % 8);
            len += padding;
            let plainBuffer : number[] = (s => { let a=[]; while(s-->0) a.push(0); return a; })(len);
            let cipherBuffer : number[] = (s => { let a=[]; while(s-->0) a.push(0); return a; })(len);
            for(let idx : number = 0; idx < plain.length; idx++) {{
                plainBuffer[idx] = plain[idx];
            };}
            for(let idx : number = 0; idx < len; idx += 8) {{
                cipher.encrypt$byte_A$int$byte_A$int(plainBuffer, idx, cipherBuffer, idx);
            };}
            return (acme.crypto.Base64.encode(cipherBuffer));
        }
    }
    SecurityUtil["__class"] = "acme.crypto.SecurityUtil";

}
namespace acme.crypto {
    export abstract class Cipher extends acme.crypto.CryptoUtils {
        public constructor(keySize : number) {
            super();
            if(this.__keySize===undefined) this.__keySize = 0;
            this.__keySize = keySize;
        }

        public __keySize : number;

        public keySize() : number {
            return this.__keySize;
        }

        public setKey$byte_A(key : number[]) { throw new Error('cannot invoke abstract overloaded method... check your argument(s) type(s)'); }

        public setKey$java_lang_String(keyStr : string) {
            this.setKey$byte_A(this.makeKey(keyStr));
        }

        public setKey(keyStr? : any) : any {
            if(((typeof keyStr === 'string') || keyStr === null)) {
                return <any>this.setKey$java_lang_String(keyStr);
            } else if(((keyStr != null && keyStr instanceof <any>Array && (keyStr.length==0 || keyStr[0] == null ||(typeof keyStr[0] === 'number'))) || keyStr === null)) {
                return <any>this.setKey$byte_A(keyStr);
            } else throw new Error('invalid overload');
        }

        public makeKey(keyStr : string) : number[] {
            let key : number[];
            if(this.__keySize === 0) key = (s => { let a=[]; while(s-->0) a.push(0); return a; })(keyStr.length); else key = (s => { let a=[]; while(s-->0) a.push(0); return a; })(this.__keySize);
            let i : number;
            let j : number;
            for(j = 0; j < key.length; ++j) {key[j] = 0;}
            for(i = 0, j = 0; i < keyStr.length; ++i, j = (j + 1) % key.length) {key[j] ^= (keyStr.charAt(i)).charCodeAt(0);}
            return key;
        }
    }
    Cipher["__class"] = "acme.crypto.Cipher";

}
namespace acme.crypto {
    export abstract class BlockCipher extends acme.crypto.Cipher {
        public constructor(keySize : number, blockSize : number) {
            super(keySize);
            if(this.__blockSize===undefined) this.__blockSize = 0;
            this.__blockSize = blockSize;
        }

        public __blockSize : number;

        public blockSize() : number {
            return this.__blockSize;
        }

        public encrypt$byte_A$int$byte_A$int(clearText : number[], clearOff : number, cipherText : number[], cipherOff : number) { throw new Error('cannot invoke abstract overloaded method... check your argument(s) type(s)'); }

        public encrypt(clearText? : any, clearOff? : any, cipherText? : any, cipherOff? : any) : any {
            if(((clearText != null && clearText instanceof <any>Array && (clearText.length==0 || clearText[0] == null ||(typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null) && ((cipherText != null && cipherText instanceof <any>Array && (cipherText.length==0 || cipherText[0] == null ||(typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null)) {
                return <any>this.encrypt$byte_A$int$byte_A$int(clearText, clearOff, cipherText, cipherOff);
            } else if(((clearText != null && clearText instanceof <any>Array && (clearText.length==0 || clearText[0] == null ||(typeof clearText[0] === 'number'))) || clearText === null) && ((clearOff != null && clearOff instanceof <any>Array && (clearOff.length==0 || clearOff[0] == null ||(typeof clearOff[0] === 'number'))) || clearOff === null) && cipherText === undefined && cipherOff === undefined) {
                return <any>this.encrypt$byte_A$byte_A(clearText, clearOff);
            } else throw new Error('invalid overload');
        }

        public decrypt$byte_A$int$byte_A$int(cipherText : number[], cipherOff : number, clearText : number[], clearOff : number) { throw new Error('cannot invoke abstract overloaded method... check your argument(s) type(s)'); }

        public decrypt(cipherText? : any, cipherOff? : any, clearText? : any, clearOff? : any) : any {
            if(((cipherText != null && cipherText instanceof <any>Array && (cipherText.length==0 || cipherText[0] == null ||(typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null) && ((clearText != null && clearText instanceof <any>Array && (clearText.length==0 || clearText[0] == null ||(typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null)) {
                return <any>this.decrypt$byte_A$int$byte_A$int(cipherText, cipherOff, clearText, clearOff);
            } else if(((cipherText != null && cipherText instanceof <any>Array && (cipherText.length==0 || cipherText[0] == null ||(typeof cipherText[0] === 'number'))) || cipherText === null) && ((cipherOff != null && cipherOff instanceof <any>Array && (cipherOff.length==0 || cipherOff[0] == null ||(typeof cipherOff[0] === 'number'))) || cipherOff === null) && clearText === undefined && clearOff === undefined) {
                return <any>this.decrypt$byte_A$byte_A(cipherText, cipherOff);
            } else throw new Error('invalid overload');
        }

        public encrypt$byte_A$byte_A(clearText : number[], cipherText : number[]) {
            this.encrypt$byte_A$int$byte_A$int(clearText, 0, cipherText, 0);
        }

        public decrypt$byte_A$byte_A(cipherText : number[], clearText : number[]) {
            this.decrypt$byte_A$int$byte_A$int(cipherText, 0, clearText, 0);
        }
    }
    BlockCipher["__class"] = "acme.crypto.BlockCipher";

}
namespace acme.crypto {
    export class IdeaCipher extends acme.crypto.BlockCipher {
        public constructor(keyStr? : any) {
            if(((typeof keyStr === 'string') || keyStr === null)) {
                let __args = arguments;
                super(16, 8);
                this.encryptKeys = (s => { let a=[]; while(s-->0) a.push(0); return a; })(52);
                this.decryptKeys = (s => { let a=[]; while(s-->0) a.push(0); return a; })(52);
                this.tempShorts = [0, 0, 0, 0];
                (() => {
                    this.setKey$java_lang_String(keyStr);
                })();
            } else if(((keyStr != null && keyStr instanceof <any>Array && (keyStr.length==0 || keyStr[0] == null ||(typeof keyStr[0] === 'number'))) || keyStr === null)) {
                let __args = arguments;
                let key : any = __args[0];
                super(16, 8);
                this.encryptKeys = (s => { let a=[]; while(s-->0) a.push(0); return a; })(52);
                this.decryptKeys = (s => { let a=[]; while(s-->0) a.push(0); return a; })(52);
                this.tempShorts = [0, 0, 0, 0];
                (() => {
                    this.setKey$byte_A(key);
                })();
            } else throw new Error('invalid overload');
        }

        /*private*/ encryptKeys : number[];

        /*private*/ decryptKeys : number[];

        public setKey$byte_A(key : number[]) {
            let k1 : number;
            let k2 : number;
            let j : number;
            let t1 : number;
            let t2 : number;
            let t3 : number;
            for(k1 = 0; k1 < 8; ++k1) {this.encryptKeys[k1] = ((key[2 * k1] & 255) << 8) | (key[2 * k1 + 1] & 255);}
            for(; k1 < 52; ++k1) {this.encryptKeys[k1] = ((this.encryptKeys[k1 - 8] << 9) | (this.encryptKeys[k1 - 7] >>> 7)) & 65535;}
            k1 = 0;
            k2 = 51;
            t1 = IdeaCipher.mulinv(this.encryptKeys[k1++]);
            t2 = -this.encryptKeys[k1++];
            t3 = -this.encryptKeys[k1++];
            this.decryptKeys[k2--] = IdeaCipher.mulinv(this.encryptKeys[k1++]);
            this.decryptKeys[k2--] = t3;
            this.decryptKeys[k2--] = t2;
            this.decryptKeys[k2--] = t1;
            for(j = 1; j < 8; ++j) {{
                t1 = this.encryptKeys[k1++];
                this.decryptKeys[k2--] = this.encryptKeys[k1++];
                this.decryptKeys[k2--] = t1;
                t1 = IdeaCipher.mulinv(this.encryptKeys[k1++]);
                t2 = -this.encryptKeys[k1++];
                t3 = -this.encryptKeys[k1++];
                this.decryptKeys[k2--] = IdeaCipher.mulinv(this.encryptKeys[k1++]);
                this.decryptKeys[k2--] = t2;
                this.decryptKeys[k2--] = t3;
                this.decryptKeys[k2--] = t1;
            };}
            t1 = this.encryptKeys[k1++];
            this.decryptKeys[k2--] = this.encryptKeys[k1++];
            this.decryptKeys[k2--] = t1;
            t1 = IdeaCipher.mulinv(this.encryptKeys[k1++]);
            t2 = -this.encryptKeys[k1++];
            t3 = -this.encryptKeys[k1++];
            this.decryptKeys[k2--] = IdeaCipher.mulinv(this.encryptKeys[k1++]);
            this.decryptKeys[k2--] = t3;
            this.decryptKeys[k2--] = t2;
            this.decryptKeys[k2--] = t1;
        }

        public setKey(key? : any) : any {
            if(((key != null && key instanceof <any>Array && (key.length==0 || key[0] == null ||(typeof key[0] === 'number'))) || key === null)) {
                return <any>this.setKey$byte_A(key);
            } else if(((typeof key === 'string') || key === null)) {
                super.setKey(key);
            } else throw new Error('invalid overload');
        }

        /*private*/ tempShorts : number[];

        public encrypt$byte_A$int$byte_A$int(clearText : number[], clearOff : number, cipherText : number[], cipherOff : number) {
            CryptoUtils.squashBytesToShorts(clearText, clearOff, this.tempShorts, 0, 4);
            this.idea(this.tempShorts, this.tempShorts, this.encryptKeys);
            CryptoUtils.spreadShortsToBytes(this.tempShorts, 0, cipherText, cipherOff, 4);
        }

        public encrypt(clearText? : any, clearOff? : any, cipherText? : any, cipherOff? : any) : any {
            if(((clearText != null && clearText instanceof <any>Array && (clearText.length==0 || clearText[0] == null ||(typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null) && ((cipherText != null && cipherText instanceof <any>Array && (cipherText.length==0 || cipherText[0] == null ||(typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null)) {
                return <any>this.encrypt$byte_A$int$byte_A$int(clearText, clearOff, cipherText, cipherOff);
            } else if(((clearText != null && clearText instanceof <any>Array && (clearText.length==0 || clearText[0] == null ||(typeof clearText[0] === 'number'))) || clearText === null) && ((clearOff != null && clearOff instanceof <any>Array && (clearOff.length==0 || clearOff[0] == null ||(typeof clearOff[0] === 'number'))) || clearOff === null) && cipherText === undefined && cipherOff === undefined) {
                return <any>this.encrypt$byte_A$byte_A(clearText, clearOff);
            } else throw new Error('invalid overload');
        }

        public decrypt$byte_A$int$byte_A$int(cipherText : number[], cipherOff : number, clearText : number[], clearOff : number) {
            CryptoUtils.squashBytesToShorts(cipherText, cipherOff, this.tempShorts, 0, 4);
            this.idea(this.tempShorts, this.tempShorts, this.decryptKeys);
            CryptoUtils.spreadShortsToBytes(this.tempShorts, 0, clearText, clearOff, 4);
        }

        public decrypt(cipherText? : any, cipherOff? : any, clearText? : any, clearOff? : any) : any {
            if(((cipherText != null && cipherText instanceof <any>Array && (cipherText.length==0 || cipherText[0] == null ||(typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null) && ((clearText != null && clearText instanceof <any>Array && (clearText.length==0 || clearText[0] == null ||(typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null)) {
                return <any>this.decrypt$byte_A$int$byte_A$int(cipherText, cipherOff, clearText, clearOff);
            } else if(((cipherText != null && cipherText instanceof <any>Array && (cipherText.length==0 || cipherText[0] == null ||(typeof cipherText[0] === 'number'))) || cipherText === null) && ((cipherOff != null && cipherOff instanceof <any>Array && (cipherOff.length==0 || cipherOff[0] == null ||(typeof cipherOff[0] === 'number'))) || cipherOff === null) && clearText === undefined && clearOff === undefined) {
                return <any>this.decrypt$byte_A$byte_A(cipherText, cipherOff);
            } else throw new Error('invalid overload');
        }

        /*private*/ idea(inShorts : number[], outShorts : number[], keys : number[]) {
            let x1 : number;
            let x2 : number;
            let x3 : number;
            let x4 : number;
            let k : number;
            let t1 : number;
            let t2 : number;
            x1 = inShorts[0];
            x2 = inShorts[1];
            x3 = inShorts[2];
            x4 = inShorts[3];
            k = 0;
            for(let round : number = 0; round < 8; ++round) {{
                x1 = IdeaCipher.mul(x1 & 65535, keys[k++]);
                x2 = x2 + keys[k++];
                x3 = x3 + keys[k++];
                x4 = IdeaCipher.mul(x4 & 65535, keys[k++]);
                t2 = x1 ^ x3;
                t2 = IdeaCipher.mul(t2 & 65535, keys[k++]);
                t1 = t2 + (x2 ^ x4);
                t1 = IdeaCipher.mul(t1 & 65535, keys[k++]);
                t2 = t1 + t2;
                x1 ^= t1;
                x4 ^= t2;
                t2 ^= x2;
                x2 = x3 ^ t1;
                x3 = t2;
            };}
            outShorts[0] = IdeaCipher.mul(x1 & 65535, keys[k++]) & 65535;
            outShorts[1] = (x3 + keys[k++]) & 65535;
            outShorts[2] = (x2 + keys[k++]) & 65535;
            outShorts[3] = IdeaCipher.mul(x4 & 65535, keys[k++]) & 65535;
        }

        /*private*/ static mul(a : number, b : number) : number {
            let ab : number = a * b;
            if(ab !== 0) {
                let lo : number = ab & 65535;
                let hi : number = ab >>> 16;
                return ((lo - hi) + (lo < hi?1:0)) & 65535;
            }
            if(a !== 0) return (1 - a) & 65535;
            return (1 - b) & 65535;
        }

        /*private*/ static mulinv(x : number) : number {
            let t0 : number;
            let t1 : number;
            let q : number;
            let y : number;
            if(x <= 1) return x;
            t0 = 1;
            t1 = (65537 / x|0);
            y = (65537 % x) & 65535;
            for(; ; ) {{
                if(y === 1) return (1 - t1) & 65535;
                q = (x / y|0);
                x = x % y;
                t0 = (t0 + q * t1) & 65535;
                if(x === 1) return t0;
                q = (y / x|0);
                y = y % x;
                t1 = (t1 + q * t0) & 65535;
            };}
        }

        public static main(args : string[]) {
            for(let a : number = 0; a < 65536; ++a) {{
                let b : number = IdeaCipher.mulinv(a);
                let c : number = IdeaCipher.mul(a, b);
                if(c !== 1) console.error("mul/mulinv flaw: " + a + " * " + b + " = " + c);
            };}
        }
    }
    IdeaCipher["__class"] = "acme.crypto.IdeaCipher";

}


acme.crypto.IdeaCipher.main(null);
