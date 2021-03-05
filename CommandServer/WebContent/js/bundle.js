/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var acme;
(function (acme) {
    var crypto;
    (function (crypto) {
        class Base64 {
            static encode(data) {
                let tbl = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'];
                let buffer = { str: "", toString: function () { return this.str; } };
                let pad = 0;
                for (let i = 0; i < data.length; i += 3) {
                    {
                        let b = ((data[i] & 255) << 16) & 16777215;
                        if (i + 1 < data.length) {
                            b |= (data[i + 1] & 255) << 8;
                        }
                        else {
                            pad++;
                        }
                        if (i + 2 < data.length) {
                            b |= (data[i + 2] & 255);
                        }
                        else {
                            pad++;
                        }
                        for (let j = 0; j < 4 - pad; j++) {
                            {
                                let c = (b & 16515072) >> 18;
                                /* append */ (sb => { sb.str = sb.str.concat(tbl[c]); return sb; })(buffer);
                                b <<= 6;
                            }
                            ;
                        }
                    }
                    ;
                }
                for (let j = 0; j < pad; j++) {
                    {
                        /* append */ (sb => { sb.str = sb.str.concat("="); return sb; })(buffer);
                    }
                    ;
                }
                return buffer.str;
            }
            static decode(data) {
                let tbl = [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1];
                let bytes = (data).split('').map(s => s.charCodeAt(0));
                let len = data.length * 6;
                if ((data.length > 1) && ((c => c.charCodeAt == null ? c : c.charCodeAt(0))(data.charAt(data.length - 2)) == '='.charCodeAt(0))) {
                    len = len - 6;
                }
                if ((data.length > 0) && ((c => c.charCodeAt == null ? c : c.charCodeAt(0))(data.charAt(data.length - 1)) == '='.charCodeAt(0))) {
                    len = len - 6;
                }
                let buffer = (s => { let a = []; while (s-- > 0)
                    a.push(0); return a; })((len / 8 | 0));
                let bufferIndex = 0;
                for (let i = 0; i < bytes.length;) {
                    {
                        let b = 0;
                        if (tbl[bytes[i]] !== -1) {
                            b = (tbl[bytes[i]] & 255) << 18;
                        }
                        else {
                            i++;
                            continue;
                        }
                        let num = 0;
                        if (i + 1 < bytes.length && tbl[bytes[i + 1]] !== -1) {
                            b = b | ((tbl[bytes[i + 1]] & 255) << 12);
                            num++;
                        }
                        if (i + 2 < bytes.length && tbl[bytes[i + 2]] !== -1) {
                            b = b | ((tbl[bytes[i + 2]] & 255) << 6);
                            num++;
                        }
                        if (i + 3 < bytes.length && tbl[bytes[i + 3]] !== -1) {
                            b = b | (tbl[bytes[i + 3]] & 255);
                            num++;
                        }
                        while ((num > 0)) {
                            {
                                let c = (b & 16711680) >> 16;
                                buffer[bufferIndex++] = (c | 0);
                                b <<= 8;
                                num--;
                            }
                        }
                        ;
                        i += 4;
                    }
                    ;
                }
                return buffer;
            }
        }
        crypto.Base64 = Base64;
        Base64["__class"] = "acme.crypto.Base64";
    })(crypto = acme.crypto || (acme.crypto = {}));
})(acme || (acme = {}));
(function (acme) {
    var crypto;
    (function (crypto) {
        class CryptoUtils {
            static zeroBlock$byte_A$int$int(block, off, len) {
                for (let i = off; i < off + len; ++i) {
                    block[i] = 0;
                }
            }
            static zeroBlock(block, off, len) {
                if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && ((typeof off === 'number') || off === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.zeroBlock$byte_A$int$int(block, off, len);
                }
                else if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && off === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.zeroBlock$byte_A(block);
                }
                else
                    throw new Error('invalid overload');
            }
            static zeroBlock$byte_A(block) {
                CryptoUtils.zeroBlock$byte_A$int$int(block, 0, block.length);
            }
            static randomBlock$byte_A$int$int(block, off, len) {
                for (let i = off; i < off + len; ++i) {
                    block[i] = ((Math.random() * 256.0) | 0);
                }
            }
            static randomBlock(block, off, len) {
                if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && ((typeof off === 'number') || off === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.randomBlock$byte_A$int$int(block, off, len);
                }
                else if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && off === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.randomBlock$byte_A(block);
                }
                else
                    throw new Error('invalid overload');
            }
            static randomBlock$byte_A(block) {
                CryptoUtils.randomBlock$byte_A$int$int(block, 0, block.length);
            }
            static xorBlock$byte_A$int$byte_A$int$byte_A$int$int(a, aOff, b, bOff, dst, dstOff, len) {
                for (let i = 0; i < len; ++i) {
                    dst[dstOff + i] = ((a[aOff + i] ^ b[bOff + i]) | 0);
                }
            }
            static xorBlock(a, aOff, b, bOff, dst, dstOff, len) {
                if (((a != null && a instanceof Array && (a.length == 0 || a[0] == null || (typeof a[0] === 'number'))) || a === null) && ((typeof aOff === 'number') || aOff === null) && ((b != null && b instanceof Array && (b.length == 0 || b[0] == null || (typeof b[0] === 'number'))) || b === null) && ((typeof bOff === 'number') || bOff === null) && ((dst != null && dst instanceof Array && (dst.length == 0 || dst[0] == null || (typeof dst[0] === 'number'))) || dst === null) && ((typeof dstOff === 'number') || dstOff === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.xorBlock$byte_A$int$byte_A$int$byte_A$int$int(a, aOff, b, bOff, dst, dstOff, len);
                }
                else if (((a != null && a instanceof Array && (a.length == 0 || a[0] == null || (typeof a[0] === 'number'))) || a === null) && ((aOff != null && aOff instanceof Array && (aOff.length == 0 || aOff[0] == null || (typeof aOff[0] === 'number'))) || aOff === null) && ((b != null && b instanceof Array && (b.length == 0 || b[0] == null || (typeof b[0] === 'number'))) || b === null) && bOff === undefined && dst === undefined && dstOff === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.xorBlock$byte_A$byte_A$byte_A(a, aOff, b);
                }
                else
                    throw new Error('invalid overload');
            }
            static xorBlock$byte_A$byte_A$byte_A(a, b, dst) {
                CryptoUtils.xorBlock$byte_A$int$byte_A$int$byte_A$int$int(a, 0, b, 0, dst, 0, a.length);
            }
            static copyBlock$byte_A$int$byte_A$int$int(src, srcOff, dst, dstOff, len) {
                for (let i = 0; i < len; ++i) {
                    dst[dstOff + i] = src[srcOff + i];
                }
            }
            static copyBlock(src, srcOff, dst, dstOff, len) {
                if (((src != null && src instanceof Array && (src.length == 0 || src[0] == null || (typeof src[0] === 'number'))) || src === null) && ((typeof srcOff === 'number') || srcOff === null) && ((dst != null && dst instanceof Array && (dst.length == 0 || dst[0] == null || (typeof dst[0] === 'number'))) || dst === null) && ((typeof dstOff === 'number') || dstOff === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.copyBlock$byte_A$int$byte_A$int$int(src, srcOff, dst, dstOff, len);
                }
                else if (((src != null && src instanceof Array && (src.length == 0 || src[0] == null || (typeof src[0] === 'number'))) || src === null) && ((srcOff != null && srcOff instanceof Array && (srcOff.length == 0 || srcOff[0] == null || (typeof srcOff[0] === 'number'))) || srcOff === null) && dst === undefined && dstOff === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.copyBlock$byte_A$byte_A(src, srcOff);
                }
                else
                    throw new Error('invalid overload');
            }
            static copyBlock$byte_A$byte_A(src, dst) {
                CryptoUtils.copyBlock$byte_A$int$byte_A$int$int(src, 0, dst, 0, src.length);
            }
            static equalsBlock$byte_A$int$byte_A$int$int(a, aOff, b, bOff, len) {
                for (let i = 0; i < len; ++i) {
                    if (a[aOff + i] !== b[bOff + i])
                        return false;
                    ;
                }
                return true;
            }
            static equalsBlock(a, aOff, b, bOff, len) {
                if (((a != null && a instanceof Array && (a.length == 0 || a[0] == null || (typeof a[0] === 'number'))) || a === null) && ((typeof aOff === 'number') || aOff === null) && ((b != null && b instanceof Array && (b.length == 0 || b[0] == null || (typeof b[0] === 'number'))) || b === null) && ((typeof bOff === 'number') || bOff === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.equalsBlock$byte_A$int$byte_A$int$int(a, aOff, b, bOff, len);
                }
                else if (((a != null && a instanceof Array && (a.length == 0 || a[0] == null || (typeof a[0] === 'number'))) || a === null) && ((aOff != null && aOff instanceof Array && (aOff.length == 0 || aOff[0] == null || (typeof aOff[0] === 'number'))) || aOff === null) && b === undefined && bOff === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.equalsBlock$byte_A$byte_A(a, aOff);
                }
                else
                    throw new Error('invalid overload');
            }
            static equalsBlock$byte_A$byte_A(a, b) {
                return CryptoUtils.equalsBlock$byte_A$int$byte_A$int$int(a, 0, b, 0, a.length);
            }
            static fillBlock$byte_A$int$byte$int(block, blockOff, b, len) {
                for (let i = blockOff; i < blockOff + len; ++i) {
                    block[i] = b;
                }
            }
            static fillBlock(block, blockOff, b, len) {
                if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && ((typeof blockOff === 'number') || blockOff === null) && ((typeof b === 'number') || b === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.fillBlock$byte_A$int$byte$int(block, blockOff, b, len);
                }
                else if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && ((typeof blockOff === 'number') || blockOff === null) && b === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.fillBlock$byte_A$byte(block, blockOff);
                }
                else
                    throw new Error('invalid overload');
            }
            static fillBlock$byte_A$byte(block, b) {
                CryptoUtils.fillBlock$byte_A$int$byte$int(block, 0, b, block.length);
            }
            static squashBytesToInts(inBytes, inOff, outInts, outOff, intLen) {
                for (let i = 0; i < intLen; ++i) {
                    outInts[outOff + i] = ((inBytes[inOff + i * 4] & 255) << 24) | ((inBytes[inOff + i * 4 + 1] & 255) << 16) | ((inBytes[inOff + i * 4 + 2] & 255) << 8) | ((inBytes[inOff + i * 4 + 3] & 255));
                }
            }
            static spreadIntsToBytes(inInts, inOff, outBytes, outOff, intLen) {
                for (let i = 0; i < intLen; ++i) {
                    {
                        outBytes[outOff + i * 4] = (((inInts[inOff + i] >>> 24) & 255) | 0);
                        outBytes[outOff + i * 4 + 1] = (((inInts[inOff + i] >>> 16) & 255) | 0);
                        outBytes[outOff + i * 4 + 2] = (((inInts[inOff + i] >>> 8) & 255) | 0);
                        outBytes[outOff + i * 4 + 3] = (((inInts[inOff + i]) & 255) | 0);
                    }
                    ;
                }
            }
            static squashBytesToIntsLittle(inBytes, inOff, outInts, outOff, intLen) {
                for (let i = 0; i < intLen; ++i) {
                    outInts[outOff + i] = ((inBytes[inOff + i * 4] & 255)) | ((inBytes[inOff + i * 4 + 1] & 255) << 8) | ((inBytes[inOff + i * 4 + 2] & 255) << 16) | ((inBytes[inOff + i * 4 + 3] & 255) << 24);
                }
            }
            static spreadIntsToBytesLittle(inInts, inOff, outBytes, outOff, intLen) {
                for (let i = 0; i < intLen; ++i) {
                    {
                        outBytes[outOff + i * 4] = (((inInts[inOff + i]) & 255) | 0);
                        outBytes[outOff + i * 4 + 1] = (((inInts[inOff + i] >>> 8) & 255) | 0);
                        outBytes[outOff + i * 4 + 2] = (((inInts[inOff + i] >>> 16) & 255) | 0);
                        outBytes[outOff + i * 4 + 3] = (((inInts[inOff + i] >>> 24) & 255) | 0);
                    }
                    ;
                }
            }
            static squashBytesToShorts(inBytes, inOff, outShorts, outOff, shortLen) {
                for (let i = 0; i < shortLen; ++i) {
                    outShorts[outOff + i] = ((inBytes[inOff + i * 2] & 255) << 8) | ((inBytes[inOff + i * 2 + 1] & 255));
                }
            }
            static spreadShortsToBytes(inShorts, inOff, outBytes, outOff, shortLen) {
                for (let i = 0; i < shortLen; ++i) {
                    {
                        outBytes[outOff + i * 2] = (((inShorts[inOff + i] >>> 8) & 255) | 0);
                        outBytes[outOff + i * 2 + 1] = (((inShorts[inOff + i]) & 255) | 0);
                    }
                    ;
                }
            }
            static squashBytesToShortsLittle(inBytes, inOff, outShorts, outOff, shortLen) {
                for (let i = 0; i < shortLen; ++i) {
                    outShorts[outOff + i] = ((inBytes[inOff + i * 2] & 255)) | ((inBytes[inOff + i * 2 + 1] & 255) << 8);
                }
            }
            static spreadShortsToBytesLittle(inShorts, inOff, outBytes, outOff, shortLen) {
                for (let i = 0; i < shortLen; ++i) {
                    {
                        outBytes[outOff + i * 2] = (((inShorts[inOff + i]) & 255) | 0);
                        outBytes[outOff + i * 2 + 1] = (((inShorts[inOff + i] >>> 8) & 255) | 0);
                    }
                    ;
                }
            }
            static toStringBlock$byte_A$int$int(block, off, len) {
                let hexits = "0123456789abcdef";
                let buf = { str: "", toString: function () { return this.str; } };
                for (let i = off; i < off + len; ++i) {
                    {
                        /* append */ (sb => { sb.str = sb.str.concat(hexits.charAt((block[i] >>> 4) & 15)); return sb; })(buf);
                        /* append */ (sb => { sb.str = sb.str.concat(hexits.charAt(block[i] & 15)); return sb; })(buf);
                    }
                    ;
                }
                return "[" + buf + "]";
            }
            static toStringBlock(block, off, len) {
                if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && ((typeof off === 'number') || off === null) && ((typeof len === 'number') || len === null)) {
                    return acme.crypto.CryptoUtils.toStringBlock$byte_A$int$int(block, off, len);
                }
                else if (((block != null && block instanceof Array && (block.length == 0 || block[0] == null || (typeof block[0] === 'number'))) || block === null) && off === undefined && len === undefined) {
                    return acme.crypto.CryptoUtils.toStringBlock$byte_A(block);
                }
                else
                    throw new Error('invalid overload');
            }
            static toStringBlock$byte_A(block) {
                return CryptoUtils.toStringBlock$byte_A$int$int(block, 0, block.length);
            }
        }
        crypto.CryptoUtils = CryptoUtils;
        CryptoUtils["__class"] = "acme.crypto.CryptoUtils";
    })(crypto = acme.crypto || (acme.crypto = {}));
})(acme || (acme = {}));
(function (acme) {
    var crypto;
    (function (crypto) {
        class SecurityUtil {
            static makeResponse(challenge, password) {
                let cipher = new acme.crypto.IdeaCipher(password);
                let plain = (challenge).split('').map(s => s.charCodeAt(0));
                let len = plain.length;
                let padding = 8 - (len % 8);
                len += padding;
                let plainBuffer = (s => { let a = []; while (s-- > 0)
                    a.push(0); return a; })(len);
                let cipherBuffer = (s => { let a = []; while (s-- > 0)
                    a.push(0); return a; })(len);
                for (let idx = 0; idx < plain.length; idx++) {
                    {
                        plainBuffer[idx] = plain[idx];
                    }
                    ;
                }
                for (let idx = 0; idx < len; idx += 8) {
                    {
                        cipher.encrypt$byte_A$int$byte_A$int(plainBuffer, idx, cipherBuffer, idx);
                    }
                    ;
                }
                return (acme.crypto.Base64.encode(cipherBuffer));
            }
        }
        crypto.SecurityUtil = SecurityUtil;
        SecurityUtil["__class"] = "acme.crypto.SecurityUtil";
    })(crypto = acme.crypto || (acme.crypto = {}));
})(acme || (acme = {}));
(function (acme) {
    var crypto;
    (function (crypto) {
        class Cipher extends acme.crypto.CryptoUtils {
            constructor(keySize) {
                super();
                if (this.__keySize === undefined)
                    this.__keySize = 0;
                this.__keySize = keySize;
            }
            keySize() {
                return this.__keySize;
            }
            setKey$byte_A(key) { throw new Error('cannot invoke abstract overloaded method... check your argument(s) type(s)'); }
            setKey$java_lang_String(keyStr) {
                this.setKey$byte_A(this.makeKey(keyStr));
            }
            setKey(keyStr) {
                if (((typeof keyStr === 'string') || keyStr === null)) {
                    return this.setKey$java_lang_String(keyStr);
                }
                else if (((keyStr != null && keyStr instanceof Array && (keyStr.length == 0 || keyStr[0] == null || (typeof keyStr[0] === 'number'))) || keyStr === null)) {
                    return this.setKey$byte_A(keyStr);
                }
                else
                    throw new Error('invalid overload');
            }
            makeKey(keyStr) {
                let key;
                if (this.__keySize === 0)
                    key = (s => { let a = []; while (s-- > 0)
                        a.push(0); return a; })(keyStr.length);
                else
                    key = (s => { let a = []; while (s-- > 0)
                        a.push(0); return a; })(this.__keySize);
                let i;
                let j;
                for (j = 0; j < key.length; ++j) {
                    key[j] = 0;
                }
                for (i = 0, j = 0; i < keyStr.length; ++i, j = (j + 1) % key.length) {
                    key[j] ^= (keyStr.charAt(i)).charCodeAt(0);
                }
                return key;
            }
        }
        crypto.Cipher = Cipher;
        Cipher["__class"] = "acme.crypto.Cipher";
    })(crypto = acme.crypto || (acme.crypto = {}));
})(acme || (acme = {}));
(function (acme) {
    var crypto;
    (function (crypto) {
        class BlockCipher extends acme.crypto.Cipher {
            constructor(keySize, blockSize) {
                super(keySize);
                if (this.__blockSize === undefined)
                    this.__blockSize = 0;
                this.__blockSize = blockSize;
            }
            blockSize() {
                return this.__blockSize;
            }
            encrypt$byte_A$int$byte_A$int(clearText, clearOff, cipherText, cipherOff) { throw new Error('cannot invoke abstract overloaded method... check your argument(s) type(s)'); }
            encrypt(clearText, clearOff, cipherText, cipherOff) {
                if (((clearText != null && clearText instanceof Array && (clearText.length == 0 || clearText[0] == null || (typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null) && ((cipherText != null && cipherText instanceof Array && (cipherText.length == 0 || cipherText[0] == null || (typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null)) {
                    return this.encrypt$byte_A$int$byte_A$int(clearText, clearOff, cipherText, cipherOff);
                }
                else if (((clearText != null && clearText instanceof Array && (clearText.length == 0 || clearText[0] == null || (typeof clearText[0] === 'number'))) || clearText === null) && ((clearOff != null && clearOff instanceof Array && (clearOff.length == 0 || clearOff[0] == null || (typeof clearOff[0] === 'number'))) || clearOff === null) && cipherText === undefined && cipherOff === undefined) {
                    return this.encrypt$byte_A$byte_A(clearText, clearOff);
                }
                else
                    throw new Error('invalid overload');
            }
            decrypt$byte_A$int$byte_A$int(cipherText, cipherOff, clearText, clearOff) { throw new Error('cannot invoke abstract overloaded method... check your argument(s) type(s)'); }
            decrypt(cipherText, cipherOff, clearText, clearOff) {
                if (((cipherText != null && cipherText instanceof Array && (cipherText.length == 0 || cipherText[0] == null || (typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null) && ((clearText != null && clearText instanceof Array && (clearText.length == 0 || clearText[0] == null || (typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null)) {
                    return this.decrypt$byte_A$int$byte_A$int(cipherText, cipherOff, clearText, clearOff);
                }
                else if (((cipherText != null && cipherText instanceof Array && (cipherText.length == 0 || cipherText[0] == null || (typeof cipherText[0] === 'number'))) || cipherText === null) && ((cipherOff != null && cipherOff instanceof Array && (cipherOff.length == 0 || cipherOff[0] == null || (typeof cipherOff[0] === 'number'))) || cipherOff === null) && clearText === undefined && clearOff === undefined) {
                    return this.decrypt$byte_A$byte_A(cipherText, cipherOff);
                }
                else
                    throw new Error('invalid overload');
            }
            encrypt$byte_A$byte_A(clearText, cipherText) {
                this.encrypt$byte_A$int$byte_A$int(clearText, 0, cipherText, 0);
            }
            decrypt$byte_A$byte_A(cipherText, clearText) {
                this.decrypt$byte_A$int$byte_A$int(cipherText, 0, clearText, 0);
            }
        }
        crypto.BlockCipher = BlockCipher;
        BlockCipher["__class"] = "acme.crypto.BlockCipher";
    })(crypto = acme.crypto || (acme.crypto = {}));
})(acme || (acme = {}));
(function (acme) {
    var crypto;
    (function (crypto) {
        class IdeaCipher extends acme.crypto.BlockCipher {
            constructor(keyStr) {
                if (((typeof keyStr === 'string') || keyStr === null)) {
                    let __args = arguments;
                    super(16, 8);
                    this.encryptKeys = (s => { let a = []; while (s-- > 0)
                        a.push(0); return a; })(52);
                    this.decryptKeys = (s => { let a = []; while (s-- > 0)
                        a.push(0); return a; })(52);
                    this.tempShorts = [0, 0, 0, 0];
                    (() => {
                        this.setKey$java_lang_String(keyStr);
                    })();
                }
                else if (((keyStr != null && keyStr instanceof Array && (keyStr.length == 0 || keyStr[0] == null || (typeof keyStr[0] === 'number'))) || keyStr === null)) {
                    let __args = arguments;
                    let key = __args[0];
                    super(16, 8);
                    this.encryptKeys = (s => { let a = []; while (s-- > 0)
                        a.push(0); return a; })(52);
                    this.decryptKeys = (s => { let a = []; while (s-- > 0)
                        a.push(0); return a; })(52);
                    this.tempShorts = [0, 0, 0, 0];
                    (() => {
                        this.setKey$byte_A(key);
                    })();
                }
                else
                    throw new Error('invalid overload');
            }
            setKey$byte_A(key) {
                let k1;
                let k2;
                let j;
                let t1;
                let t2;
                let t3;
                for (k1 = 0; k1 < 8; ++k1) {
                    this.encryptKeys[k1] = ((key[2 * k1] & 255) << 8) | (key[2 * k1 + 1] & 255);
                }
                for (; k1 < 52; ++k1) {
                    this.encryptKeys[k1] = ((this.encryptKeys[k1 - 8] << 9) | (this.encryptKeys[k1 - 7] >>> 7)) & 65535;
                }
                k1 = 0;
                k2 = 51;
                t1 = IdeaCipher.mulinv(this.encryptKeys[k1++]);
                t2 = -this.encryptKeys[k1++];
                t3 = -this.encryptKeys[k1++];
                this.decryptKeys[k2--] = IdeaCipher.mulinv(this.encryptKeys[k1++]);
                this.decryptKeys[k2--] = t3;
                this.decryptKeys[k2--] = t2;
                this.decryptKeys[k2--] = t1;
                for (j = 1; j < 8; ++j) {
                    {
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
                    }
                    ;
                }
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
            setKey(key) {
                if (((key != null && key instanceof Array && (key.length == 0 || key[0] == null || (typeof key[0] === 'number'))) || key === null)) {
                    return this.setKey$byte_A(key);
                }
                else if (((typeof key === 'string') || key === null)) {
                    super.setKey(key);
                }
                else
                    throw new Error('invalid overload');
            }
            encrypt$byte_A$int$byte_A$int(clearText, clearOff, cipherText, cipherOff) {
                crypto.CryptoUtils.squashBytesToShorts(clearText, clearOff, this.tempShorts, 0, 4);
                this.idea(this.tempShorts, this.tempShorts, this.encryptKeys);
                crypto.CryptoUtils.spreadShortsToBytes(this.tempShorts, 0, cipherText, cipherOff, 4);
            }
            encrypt(clearText, clearOff, cipherText, cipherOff) {
                if (((clearText != null && clearText instanceof Array && (clearText.length == 0 || clearText[0] == null || (typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null) && ((cipherText != null && cipherText instanceof Array && (cipherText.length == 0 || cipherText[0] == null || (typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null)) {
                    return this.encrypt$byte_A$int$byte_A$int(clearText, clearOff, cipherText, cipherOff);
                }
                else if (((clearText != null && clearText instanceof Array && (clearText.length == 0 || clearText[0] == null || (typeof clearText[0] === 'number'))) || clearText === null) && ((clearOff != null && clearOff instanceof Array && (clearOff.length == 0 || clearOff[0] == null || (typeof clearOff[0] === 'number'))) || clearOff === null) && cipherText === undefined && cipherOff === undefined) {
                    return this.encrypt$byte_A$byte_A(clearText, clearOff);
                }
                else
                    throw new Error('invalid overload');
            }
            decrypt$byte_A$int$byte_A$int(cipherText, cipherOff, clearText, clearOff) {
                crypto.CryptoUtils.squashBytesToShorts(cipherText, cipherOff, this.tempShorts, 0, 4);
                this.idea(this.tempShorts, this.tempShorts, this.decryptKeys);
                crypto.CryptoUtils.spreadShortsToBytes(this.tempShorts, 0, clearText, clearOff, 4);
            }
            decrypt(cipherText, cipherOff, clearText, clearOff) {
                if (((cipherText != null && cipherText instanceof Array && (cipherText.length == 0 || cipherText[0] == null || (typeof cipherText[0] === 'number'))) || cipherText === null) && ((typeof cipherOff === 'number') || cipherOff === null) && ((clearText != null && clearText instanceof Array && (clearText.length == 0 || clearText[0] == null || (typeof clearText[0] === 'number'))) || clearText === null) && ((typeof clearOff === 'number') || clearOff === null)) {
                    return this.decrypt$byte_A$int$byte_A$int(cipherText, cipherOff, clearText, clearOff);
                }
                else if (((cipherText != null && cipherText instanceof Array && (cipherText.length == 0 || cipherText[0] == null || (typeof cipherText[0] === 'number'))) || cipherText === null) && ((cipherOff != null && cipherOff instanceof Array && (cipherOff.length == 0 || cipherOff[0] == null || (typeof cipherOff[0] === 'number'))) || cipherOff === null) && clearText === undefined && clearOff === undefined) {
                    return this.decrypt$byte_A$byte_A(cipherText, cipherOff);
                }
                else
                    throw new Error('invalid overload');
            }
            /*private*/ idea(inShorts, outShorts, keys) {
                let x1;
                let x2;
                let x3;
                let x4;
                let k;
                let t1;
                let t2;
                x1 = inShorts[0];
                x2 = inShorts[1];
                x3 = inShorts[2];
                x4 = inShorts[3];
                k = 0;
                for (let round = 0; round < 8; ++round) {
                    {
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
                    }
                    ;
                }
                outShorts[0] = IdeaCipher.mul(x1 & 65535, keys[k++]) & 65535;
                outShorts[1] = (x3 + keys[k++]) & 65535;
                outShorts[2] = (x2 + keys[k++]) & 65535;
                outShorts[3] = IdeaCipher.mul(x4 & 65535, keys[k++]) & 65535;
            }
            /*private*/ static mul(a, b) {
                let ab = a * b;
                if (ab !== 0) {
                    let lo = ab & 65535;
                    let hi = ab >>> 16;
                    return ((lo - hi) + (lo < hi ? 1 : 0)) & 65535;
                }
                if (a !== 0)
                    return (1 - a) & 65535;
                return (1 - b) & 65535;
            }
            /*private*/ static mulinv(x) {
                let t0;
                let t1;
                let q;
                let y;
                if (x <= 1)
                    return x;
                t0 = 1;
                t1 = (65537 / x | 0);
                y = (65537 % x) & 65535;
                for (;;) {
                    {
                        if (y === 1)
                            return (1 - t1) & 65535;
                        q = (x / y | 0);
                        x = x % y;
                        t0 = (t0 + q * t1) & 65535;
                        if (x === 1)
                            return t0;
                        q = (y / x | 0);
                        y = y % x;
                        t1 = (t1 + q * t0) & 65535;
                    }
                    ;
                }
            }
            static main(args) {
                for (let a = 0; a < 65536; ++a) {
                    {
                        let b = IdeaCipher.mulinv(a);
                        let c = IdeaCipher.mul(a, b);
                        if (c !== 1)
                            console.error("mul/mulinv flaw: " + a + " * " + b + " = " + c);
                    }
                    ;
                }
            }
        }
        crypto.IdeaCipher = IdeaCipher;
        IdeaCipher["__class"] = "acme.crypto.IdeaCipher";
    })(crypto = acme.crypto || (acme.crypto = {}));
})(acme || (acme = {}));
acme.crypto.IdeaCipher.main(null);
